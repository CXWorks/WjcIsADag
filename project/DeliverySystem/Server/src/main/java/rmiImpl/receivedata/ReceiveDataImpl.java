package rmiImpl.receivedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import message.OperationMessage;
import po.FormEnum;
import po.receivedata.ReceivePO;
import rmi.receivedata.ReceiveDataService;
import rmiImpl.CommonData;
import rmiImpl.ConnecterHelper;

/**
 * 
 * @author wjc 2015/10/24
 */

public class ReceiveDataImpl extends CommonData<ReceivePO> implements
		ReceiveDataService {

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public ReceiveDataImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		Table_Name = "receive";
		conn = ConnecterHelper.connSQL(conn);
	}

	public Connection getConn() {
		return conn;
	}

	public OperationMessage insert(ReceivePO po) {
		// TODO Auto-generated method stub
		String insert = "insert into " + Table_Name
				+ "(formID,formState,orderID,transitID,data,depature,state) "
				+ "values('" + po.getFormID() + "','"
				+ po.getFormState().toString() + "','" + po.getOrderID()
				+ "','" + po.getTransitID() + "','" + po.getData().toString() + "','"
				+ po.getDepature() + "','" + po.getState() + "')";

		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("新建时出错：");
			e.printStackTrace();
		}

		return new OperationMessage();
	}

	public OperationMessage delete(String id) {
		// TODO Auto-generated method stub
		String delete = "delete from " + Table_Name + " where formID= '" + id
				+ "'";
		try {
			statement = conn.prepareStatement(delete);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("删除时出错：");
			e.printStackTrace();
		}
		return new OperationMessage();
	}

	public OperationMessage update(ReceivePO po) {
		// TODO Auto-generated method stub
		ArrayList<String> operations = new ArrayList<String>();
		try {
			ReceivePO old = getFormPO(po.getFormID());

			if (old.getFormState() != po.getFormState())
				operations.add("update " + Table_Name + " set formState ="
						+ po.getFormState().toString() + " where formID= '"
						+ po.getFormID() + "'");
			if (!old.getOrderID().equalsIgnoreCase(po.getOrderID()))
				operations.add("update " + Table_Name + " set orderID ="
						+ po.getOrderID() + " where formID= '" + po.getFormID()
						+ "'");
			if (!old.getTransitID().equalsIgnoreCase(po.getTransitID()))
				operations.add("update " + Table_Name + " set transitID ="
						+ po.getTransitID() + " where formID= '"
						+ po.getFormID() + "'");
			if (!old.getData().equals(po.getData()))
				operations.add("update " + Table_Name + " set data ="
						+ po.getData() + " where formID= '" + po.getFormID()
						+ "'");
			if (!old.getDepature().equalsIgnoreCase(po.getDepature()))
				operations.add("update " + Table_Name + " set depature ="
						+ po.getDepature() + " where formID= '"
						+ po.getFormID() + "'");
			if (old.getState() != po.getState())
				operations.add("update " + Table_Name + " set state ="
						+ po.getState().toString() + " where formID= '"
						+ po.getFormID() + "'");
			// System.out.println(operations.size());

			for (String tmp : operations) {
				statement = conn.prepareStatement(tmp);
				statement.executeUpdate();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("修改数据库时出错：");
			e.printStackTrace();
		}
		return new OperationMessage();
	}

	public String newID() {
		// TODO Auto-generated method stub
		return "1000101";
	}

	public OperationMessage clear() {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public ReceivePO getFormPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		String select = "select * from " + Table_Name + " where formID= '" + id
				+ "'";
		ResultSet rs = null;
		ReceivePO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new ReceivePO(rs.getString("orderID"),
					rs.getString("transitID"), rs.getTimestamp("data"),
					rs.getString("depature"), rs.getString("state"));
			result.setFormType(FormEnum.RECEIVE);
			result.setFormID(rs.getString("formID"));
			result.setFormState(rs.getString("formState"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<ReceivePO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from " + Table_Name;
		ResultSet rs = null;
		ReceivePO temp = null;
		ArrayList<ReceivePO> result = new ArrayList<ReceivePO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new ReceivePO(rs.getString("orderID"),
						rs.getString("transitID"), rs.getTimestamp("data"),
						rs.getString("depature"), rs.getString("state"));
				temp.setFormType(FormEnum.RECEIVE);
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

}
