package rmiImpl.financedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import message.OperationMessage;
import po.financedata.PaymentPO;
import po.financedata.RevenuePO;
import po.receivedata.ReceivePO;
import rmi.financedata.RevenueDataService;
import database.ConnecterHelper;

public class RevenueDataImpl extends UnicastRemoteObject implements RevenueDataService {

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public RevenueDataImpl() throws RemoteException {
		super();
		Table_Name = "revenue";
		conn = ConnecterHelper.getConn();
	}

	@Override
	public Connection getConn() throws RemoteException {
		return conn;
	}

	@Override
	public OperationMessage insert(RevenuePO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String IDs = "";
		ArrayList<String> list = po.getOrderIDs();
		for (int i = 0; i < list.size(); i++)
			if (i == list.size() - 1)
				IDs += list.get(i);
			else
				IDs += list.get(i) + " ";
		;

		String insert = "insert into `" + Table_Name
				+ "`(formID,formState,date,amount,deliverName,hallID,orderIDs,date_and_unit) " + "values('"
				+ po.getFormID() + "','" + po.getFormState().toString() + "','" + po.getDateForSQL().toString() + "','"
				+ po.getAmount() + "','" + po.getDeliverName() + "','" + po.getHallID() + "','" + IDs
				+ "','" + po.getFormID().substring(2, 17) + "')";
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

	@Override
	public RevenuePO getFormPO(String id) throws RemoteException {
		String select = "select * from `" + Table_Name + "` where `formID` = '" + id + "'";
		ResultSet rs = null;
		RevenuePO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			ArrayList<String> IDs = new ArrayList<String>();
			if (!rs.getString("IDs").equalsIgnoreCase("")) {
				IDs = new ArrayList<String>(Arrays.asList(rs.getString("orderIDs").split(" ")));
			}
			result = new RevenuePO(rs.getString("formID"), rs.getTimestamp("date"), rs.getString("amount"),
					rs.getString("deliverName"), rs.getString("hallID"),IDs);
			result.setFormState(rs.getString("formState"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
		return result;
	}

	@Override
	public OperationMessage delete(String id) throws RemoteException {
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

	@Override
	public OperationMessage update(RevenuePO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		if (!this.delete(po.getFormID()).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应表单");
		if (!this.insert(po).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

	@Override
	public String newID(String unitID) throws RemoteException {
		ResultSet rs = null;
		int ID_MAX = 0;
		String temp = new Timestamp(System.currentTimeMillis()).toString().substring(0, 10);
		String target = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8);
		target = unitID + target;// 单位编号+当天日期
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

		return "02" + target + added;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
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

	@Override
	public ArrayList<RevenuePO> getAll() throws RemoteException {
		String selectAll = "select * from `" + Table_Name + "`";
		ResultSet rs = null;
		RevenuePO temp = null;
		ArrayList<RevenuePO> result = new ArrayList<RevenuePO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				ArrayList<String> IDs = new ArrayList<String>();
				if (!rs.getString("IDs").equalsIgnoreCase("")) {
					IDs = new ArrayList<String>(Arrays.asList(rs.getString("orderIDs").split(" ")));
				}
				temp = new RevenuePO(rs.getString("formID"), rs.getTimestamp("date"), rs.getString("amount"),
						rs.getString("deliverName"), rs.getString("hallID"), IDs);
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
	public ArrayList<RevenuePO> getByHallID(String ID) throws RemoteException {
		String select = "select * from `" + Table_Name + "` where `hallID` = '" + ID + "'";
		ResultSet rs = null;
		RevenuePO temp = null;
		ArrayList<RevenuePO> result = new ArrayList<RevenuePO>();
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) {
				ArrayList<String> IDs = new ArrayList<String>();
				if (!rs.getString("orderIDs").equalsIgnoreCase("")) {
					IDs = new ArrayList<String>(Arrays.asList(rs.getString("orderIDs").split(" ")));
				}
				temp = new RevenuePO(rs.getString("formID"), rs.getTimestamp("date"), rs.getString("amount"),
						rs.getString("deliverName"), rs.getString("hallID"), IDs);
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
	public ArrayList<RevenuePO> getByTime(Calendar start, Calendar end) throws RemoteException {
		String select = "select * from `" + Table_Name + "` where '" + start.getTime().getTime() / 1000
				+ "' < UNIX_TIMESTAMP(`date`) " + "and '" + end.getTime().getTime() / 1000
				+ "' > UNIX_TIMESTAMP(`date`)";
		ResultSet rs = null;
		RevenuePO temp = null;
		ArrayList<RevenuePO> result = new ArrayList<RevenuePO>();
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) {
				ArrayList<String> IDs = new ArrayList<String>();
				if (!rs.getString("orderIDs").equalsIgnoreCase("")) {
					IDs = new ArrayList<String>(Arrays.asList(rs.getString("orderIDs").split(" ")));
				}
				temp = new RevenuePO(rs.getString("formID"), rs.getTimestamp("date"), rs.getString("amount"),
						rs.getString("deliverName"), rs.getString("hallID"), IDs);
				temp.setFormState(rs.getString("formState"));
				result.add(temp);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

}
