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
import database.ConnecterHelper;

/**
 *
 * @author wjc 2015/10/24
 */

public class DeliverDataImpl extends CommonData<DeliverPO> implements DeliverDataService {

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;
	private String today = "";// 格式eg.2015-11-22
	private int ID_MAX;

	public DeliverDataImpl() throws RemoteException {
		super();
		Table_Name = "deliver";
		conn = ConnecterHelper.getConn();

		// 为today和ID_MAX初始化
		this.newID(null);
		ID_MAX--;
	}

	public Connection getConn() {
		return conn;
	}

	public OperationMessage insert(DeliverPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String insert = "insert into `" + Table_Name
				+ "`(formID,formState,orderID,postman,date,finished,date_and_unit) " + "values('" + po.getFormID()
				+ "','" + po.getFormState().toString() + "','" + po.getOrderID() + "','" + po.getPostman() + "','"
				+ po.getDateForSQL().toString() + "','" + po.isFinished() + "','" + po.getFormID().substring(2, 17)
				+ "')";

		String setFinished = "updata `order` set `finished` = '1' where `formID` = '" + po.getOrderID() + "'";

		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			if (this.getFormPO(po.getFormID()) != null) {
				po.setFormID(this.newID(po.getFormID().substring(9, 17)));
				this.insert(po);
			} else {
				result = new OperationMessage(false, "新建时出错：");
				System.err.println("新建时出错：");
				e.printStackTrace();
			}
		}
		try {
			if (po.isFinished()) {
				statement = conn.prepareStatement(setFinished);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			result = new OperationMessage(false, "修改order时时出错：");
			System.err.println("修改order时时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public OperationMessage delete(String id) {
		OperationMessage result = new OperationMessage();
		String delete = "delete from `" + Table_Name + "` where `formID` = '" + id + "'";
		try {
			statement = conn.prepareStatement(delete);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "删除时出错：");
			System.err.println("删除时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public OperationMessage update(DeliverPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		if (!this.delete(po.getFormID()).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应表单");
		if (!this.insert(po).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

	public String newID(String unitID) {
		ResultSet rs = null;
		String date = new Timestamp(System.currentTimeMillis()).toString().substring(0, 10);
		String target = date.substring(0, 4) + date.substring(5, 7) + date.substring(8);
		target = unitID + target;// 开具单位编号+当天日期

		// 当前日期与缓存日期一致
		if (date.equalsIgnoreCase(today)) {
			this.ID_MAX++;
			String added = String.format("%07d", ID_MAX);
			return "04" + target + added;
		}

		// 当前日期与缓存日期不一致
		today = date;
		String selectAll = "select * from `" + Table_Name + "` where `date_and_unit` = '" + target + "'";
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				ID_MAX = Math.max(ID_MAX, Integer.parseInt(rs.getString("formID").substring(17)));// 最后7位编号
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
		OperationMessage result = new OperationMessage();
		String clear = "delete from `" + Table_Name + "`";
		try {
			statement = conn.prepareStatement(clear);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "清空数据库时出错：");
			System.err.println("清空数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public DeliverPO getFormPO(String id) throws RemoteException {
		String select = "select * from `" + Table_Name + "` where `formID` = '" + id + "'";
		ResultSet rs = null;
		DeliverPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new DeliverPO(rs.getString("formID"), rs.getString("orderID"), rs.getTimestamp("date"),
					rs.getString("postman"));
			result.setFormState(rs.getString("formState"));
			result.setFinished(rs.getBoolean("finished"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public ArrayList<DeliverPO> getAll() throws RemoteException {
		String selectAll = "select * from `" + Table_Name + "`";
		ResultSet rs = null;
		DeliverPO temp = null;
		ArrayList<DeliverPO> result = new ArrayList<DeliverPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new DeliverPO(rs.getString("formID"), rs.getString("orderID"), rs.getTimestamp("date"),
						rs.getString("postman"));
				temp.setFormState(rs.getString("formState"));
				temp.setFinished(rs.getBoolean("finished"));
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
		ArrayList<String> result = new ArrayList<String>();
		String select = "select * from " + "`order`" + " where `targetHallID` = '" + HallID + "' and `finished` = '" + 0
				+ "'";
		ResultSet rs = null;

		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) { // 遍历order表，查其中FromIDs中是否有为targetHallID的到达单
				ArrayList<String> FormIDs = new ArrayList<String>(Arrays.asList(rs.getString("FormIDs").split(" ")));
				String last = FormIDs.get(FormIDs.size() - 1);
				// System.out.println(last);
				if (last.substring(0, 9).equalsIgnoreCase("03" + HallID)) {
					result.add(rs.getString("formID"));
				}
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public ArrayList<String> searchAsPerson(String ID) throws RemoteException {
		ArrayList<String> result = new ArrayList<String>();
		String select = "select * from `" + Table_Name + "` where `postman` = '" + ID + "' and `finished` = '" + 0
				+ "'";
		ResultSet rs = null;

		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) {
				result.add(rs.getString("formID"));
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}
}
