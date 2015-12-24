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
import java.util.HashMap;

import message.OperationMessage;
import po.FormEnum;
import po.deliverdata.DeliverPO;
import po.orderdata.OrderPO;
import po.receivedata.ReceivePO;
import rmi.deliverdata.DeliverDataService;
import rmiImpl.CommonData;
import database.ConnecterHelper;
import database.MySql;
import database.enums.TableEnum;

/**
 *
 * @author wjc 2015/10/24
 */

public class DeliverDataImpl extends CommonData<DeliverPO> implements DeliverDataService {

	private Connection conn = null;
	private PreparedStatement statement = null;
	private String today = "";// 格式eg.2015-11-22
	private int ID_MAX;

	public DeliverDataImpl() throws RemoteException {
		super();
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
		String insert = MySql.insert(TableEnum.DELIVER, new HashMap<String, String>() {
			{
				put("formID", po.getFormID());
				put("formState", po.getFormState().toString());
				put("orderID", po.getOrderID());
				put("postman", po.getPostman());
				put("date", po.getDateForSQL().toString());
				put("finished", po.isFinished() + "");
				put("date_and_unit", po.getFormID().substring(2, 17));
			}
		});
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
		return result;
	}

	public OperationMessage delete(String id) {
		OperationMessage result = new OperationMessage();
		String delete = MySql.delete(TableEnum.DELIVER, new HashMap<String, String>() {
			{
				put("formID", id);
			}
		});
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

//	public static void main(String[] args) throws RemoteException {
//		DeliverDataImpl t = new DeliverDataImpl();
//		DeliverPO po = t.getFormPO("040251001201512240000006");
//		po.setFinished(false);
//		t.update(po);
//	}

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
		final String target = unitID + date.substring(0, 4) + date.substring(5, 7) + date.substring(8);// 开具单位编号+当天日期

		// 当前日期与缓存日期一致
		if (date.equalsIgnoreCase(today)) {
			this.ID_MAX++;
			String added = String.format("%07d", ID_MAX);
			return "04" + target + added;
		}

		// 当前日期与缓存日期不一致
		today = date;
		String selectAll = MySql.select(TableEnum.DELIVER, new HashMap<String, String>() {
			{
				put("date_and_unit", target);
			}
		});
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
		String clear = MySql.delete(TableEnum.DELIVER, null);
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
		String select = MySql.select(TableEnum.DELIVER, new HashMap<String, String>() {
			{
				put("formID", id);
			}
		});
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
		String selectAll = MySql.select(TableEnum.DELIVER, null);
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
		String select = MySql.select(TableEnum.ORDER, new HashMap<String, String>() {
			{
				put("targetHallID", HallID);
				put("finished", "0");
			}
		});
		ResultSet rs = null;

		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) { // 遍历order表，查其中FromIDs中是否有为targetHallID的到达单
				ArrayList<String> FormIDs = new ArrayList<String>(Arrays.asList(rs.getString("FormIDs").split(" ")));
				String last = FormIDs.get(FormIDs.size() - 1);
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
		String select = MySql.select(TableEnum.DELIVER, new HashMap<String, String>() {
			{
				put("postman", ID);
				put("finished", "0");
			}
		});
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
