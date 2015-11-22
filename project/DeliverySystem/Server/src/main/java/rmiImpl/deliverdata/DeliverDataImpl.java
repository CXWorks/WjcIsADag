package rmiImpl.deliverdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import message.OperationMessage;
import po.FormEnum;
import po.deliverdata.DeliverPO;
import po.orderdata.OrderPO;
import po.receivedata.ReceivePO;
import rmi.deliverdata.DeliverDataService;
import rmiImpl.CommonData;
import rmiImpl.ConnecterHelper;

/**
 * 
 * @author wjc 2015/10/24
 */

public class DeliverDataImpl extends CommonData<DeliverPO> implements
		DeliverDataService {

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public DeliverDataImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		Table_Name = "deliver";
		conn = ConnecterHelper.connSQL(conn);
	}

	public Connection getConn() {
		return conn;
	}

	public OperationMessage insert(DeliverPO po) {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String insert = "insert into " + Table_Name
				+ "(formID,formState,orderID,postman,date) " + "values('"
				+ po.getFormID() + "','" + po.getFormState().toString() + "','"
				+ po.getOrderID() + "','" + po.getPostman() + "','"
				+ po.getDateForSQL().toString() + "')";

		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "新建时出错：");
			System.err.println("新建时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage delete(String id) {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String delete = "delete from " + Table_Name + " where formID= '" + id
				+ "'";
		try {
			statement = conn.prepareStatement(delete);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "删除时出错：");
			System.err.println("删除时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public OperationMessage update(DeliverPO po) {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		if (!this.delete(po.getFormID()).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应表单");
		if (!this.insert(po).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

	public String newID(String unitID) {
		// TODO Auto-generated method stub
		String selectAll = "select * from " + Table_Name;
		ResultSet rs = null;
		int ID_MAX = 0;
		String temp = new Timestamp(System.currentTimeMillis()).toString()
				.substring(0, 10);
		String target = temp.substring(0, 4) + temp.substring(5, 7)
				+ temp.substring(8);
		target = unitID + target;// 开具单位编号+当天日期
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = rs.getString("formID").substring(2, 17);
				if (target.equalsIgnoreCase(temp))
					ID_MAX = Math.max(
							ID_MAX,
							Integer.parseInt(rs.getString("formID").substring(
									17)));// 最后6位编号
			}
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}

		ID_MAX++;// 将该数字加一
		if (ID_MAX > 9999999)
			return null;
		String added = String.format("%07d", ID_MAX);

		return "04" + target + added;
	}

	public OperationMessage clear() {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String clear = "delete from " + Table_Name;
		try {
			statement = conn.prepareStatement(clear);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "清空数据库时出错：");
			System.err.println("清空数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public DeliverPO getFormPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		String select = "select * from " + Table_Name + " where formID= '" + id
				+ "'";
		ResultSet rs = null;
		DeliverPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new DeliverPO(rs.getString("orderID"),
					rs.getTimestamp("date"), rs.getString("postman"));
			result.setFormType(FormEnum.DELIVER);
			result.setFormID(rs.getString("formID"));
			result.setFormState(rs.getString("formState"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<DeliverPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from " + Table_Name;
		ResultSet rs = null;
		DeliverPO temp = null;
		ArrayList<DeliverPO> result = new ArrayList<DeliverPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new DeliverPO(rs.getString("orderID"),
						rs.getTimestamp("date"), rs.getString("postman"));
				temp.setFormType(FormEnum.DELIVER);
				temp.setFormID(rs.getString("formID"));
				temp.setFormState(rs.getString("formState"));
				result.add(temp);

			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public ArrayList<String> available(String HallID) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<String>();
		String select = "select * from " + "order";
		ResultSet rs = null;
		ArrayList<String> FormIDs = null;

		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) { // 遍历order表，查其中FromIDs中是否有为targetHallID的到达单
				FormIDs = (ArrayList<String>) Arrays.asList(rs.getString(
						"FormIDs").split(" "));
				String targetHallID = rs.getString("targetHallID");
				for (String tmp : FormIDs) {
					if (tmp.substring(0, 9).equalsIgnoreCase(
							"03" + targetHallID)) {
						result.add(rs.getString("formID"));
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}
}
