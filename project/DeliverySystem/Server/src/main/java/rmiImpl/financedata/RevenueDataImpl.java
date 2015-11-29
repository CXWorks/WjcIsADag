package rmiImpl.financedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import message.OperationMessage;
import po.financedata.RevenuePO;
import po.receivedata.ReceivePO;
import rmi.financedata.RevenueDataService;
import rmiImpl.ConnecterHelper;

public class RevenueDataImpl extends UnicastRemoteObject implements
		RevenueDataService {

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public RevenueDataImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		Table_Name = "revenue";
		conn = ConnecterHelper.connSQL(conn);
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return conn;
	}

	@Override
	public OperationMessage insert(RevenuePO po) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String insert = "insert into " + Table_Name
				+ "(formID,formState,data,amount,deliverName,hallID,orderID) "
				+ "values('" + po.getFormID() + "','"
				+ po.getFormState().toString() + "','"
				+ po.getDateForSQL().toString() + "','" + po.getAmount()
				+ "','" + po.getDeliverName() + "','" + po.getHallID() + "','"
				+ po.getOrderID() + "')";

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

	@Override
	public RevenuePO getFormPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		String select = "select * from " + Table_Name + " where formID= '" + id
				+ "'";
		ResultSet rs = null;
		RevenuePO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new RevenuePO(rs.getString("formID"),
					rs.getTimestamp("date"), rs.getString("amount"),
					rs.getString("deliverName"), rs.getString("hallID"),
					rs.getString("orderID"));
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

	@Override
	public OperationMessage update(RevenuePO po) throws RemoteException {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		String selectAll = "select * from " + Table_Name;
		ResultSet rs = null;
		int ID_MAX = 0;
		String temp = new Timestamp(System.currentTimeMillis()).toString()
				.substring(0, 10);
		String target = temp.substring(0, 4) + temp.substring(5, 7)
				+ temp.substring(8);
		target = unitID + target;// 单位编号+当天日期
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = rs.getString("formID").substring(2, 17);
				if (target.equalsIgnoreCase(temp))
					ID_MAX = Math.max(
							ID_MAX,
							Integer.parseInt(rs.getString("formID").substring(
									17)));// 最后7位编号
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

	@Override
	public ArrayList<RevenuePO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from " + Table_Name;
		ResultSet rs = null;
		RevenuePO temp = null;
		ArrayList<RevenuePO> result = new ArrayList<RevenuePO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new RevenuePO(rs.getString("formID"),
						rs.getTimestamp("date"), rs.getString("amount"),
						rs.getString("deliverName"), rs.getString("hallID"),
						rs.getString("orderID"));
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
		// TODO Auto-generated method stub
		String select = "select * from " + Table_Name + "where hallID = '" + ID + "'";
		ResultSet rs = null;
		RevenuePO temp = null;
		ArrayList<RevenuePO> result = new ArrayList<RevenuePO>();
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) {
				temp = new RevenuePO(rs.getString("formID"),
						rs.getTimestamp("date"), rs.getString("amount"),
						rs.getString("deliverName"), rs.getString("hallID"),
						rs.getString("orderID"));
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
