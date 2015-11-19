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
		return new OperationMessage();
	}

	public OperationMessage delete(String id) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage update(ReceivePO po) {
		// TODO Auto-generated method stub
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
//			statement = conn.prepareStatement(select);
//			rs = statement.executeQuery(select);
//			rs.next();
//			result = new ReceivePO(rs.getString("orderID"),
//					rs.getString("transitID"), rs.getString("data"),
//					rs.getString("depature"), rs.getString("state"));
//			result.setFormType(FormEnum.RECEIVE);
//			result.setFormID(rs.getString("formID"));
//			result.setFormState(rs.getString("formState"));
//			
//			System.out.println(rs.getString("formID"));
//			System.out.println(rs.getString("formState"));
//			System.out.println(rs.getString("orderID"));
//			System.out.println(rs.getString("transitID"));
//			System.out.println(rs.getString("data"));
//			System.out.println(rs.getString("depature"));
//			System.out.println(rs.getString("state"));
		} catch (SQLException e) {
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
//				temp = new ReceivePO(rs.getString("orderID"),
//						rs.getString("transitID"), rs.getString("data"),
//						rs.getString("depature"), rs.getString("state"));
//				temp.setFormType(FormEnum.RECEIVE);
//				temp.setFormID(rs.getString("formID"));
//				temp.setFormState(rs.getString("formState"));
//				result.add(temp);
				
//				System.out.println(rs.getString("formID"));
//				System.out.println(rs.getString("formState"));
//				System.out.println(rs.getString("orderID"));
//				System.out.println(rs.getString("transitID"));
//				System.out.println(rs.getString("data"));
//				System.out.println(rs.getString("depature"));
//				System.out.println(rs.getString("state"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
