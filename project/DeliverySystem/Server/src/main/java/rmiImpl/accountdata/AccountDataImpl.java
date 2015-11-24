package rmiImpl.accountdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import message.OperationMessage;
import po.FormEnum;
import po.accountdata.AccountPO;
import po.companydata.CenterPO;
import po.receivedata.ReceivePO;
import rmi.accountdata.AccountDataService;
import rmiImpl.ConnecterHelper;

/**
 * 
 * @author mx 2015/10/25
 */

public class AccountDataImpl extends UnicastRemoteObject implements
		AccountDataService {

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public AccountDataImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		Table_Name = "account";
		conn = ConnecterHelper.connSQL(conn);
	}

	public Connection getConn() {
		return conn;
	}

	public AccountPO getAccountPO(String accountID) {
		// TODO Auto-generated method stub
		String select = "select * from " + Table_Name + " where ID= '" + accountID
				+ "'";
		ResultSet rs = null;
		AccountPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new AccountPO(rs.getString("ID"),
					rs.getString("authority"), rs.getString("password"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public ArrayList<AccountPO> getAccountPOs() {
		// TODO Auto-generated method stub
		String selectAll = "select * from " + Table_Name;
		ResultSet rs = null;
		AccountPO temp = null;
		ArrayList<AccountPO> result = new ArrayList<AccountPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new AccountPO(rs.getString("ID"),
						rs.getString("authority"), rs.getString("password"));
				result.add(temp);

			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage insert(AccountPO po) {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String insert = "insert into " + Table_Name
				+ "(ID,password,authority) " + "values('" + po.getID() + "','"
				+ po.getPassword() + "','" + po.getAuthority().toString()
				+ "')";

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

	public OperationMessage delete(String accountID) {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String delete = "delete from " + Table_Name + " where ID= '"
				+ accountID + "'";
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

	public OperationMessage update(AccountPO po) {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		if (!this.delete(po.getID()).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应账号");
		if (!this.insert(po).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;

	}

	public OperationMessage checkAccount(String id, String password) {
		// TODO Auto-generated method stub 
		OperationMessage result = new OperationMessage();
		AccountPO a = this.getAccountPO(id);
		if(a==null)
			result =new OperationMessage(false,"账户不存在");
		else if(!password.equalsIgnoreCase(a.getPassword()))
			result =new OperationMessage(false,"账户密码不匹配");
		return result;
	}

//	public String newAccountID(String type,String city) {
//		// TODO Auto-generated method stub
//		String selectAll = "select * from " + Table_Name;
//		ResultSet rs = null;
//		int ID_MAX = 0;
//		String target = type + city;// 工种+城市
//		try {+
//			statement = conn.prepareStatement(selectAll);
//			rs = statement.executeQuery(selectAll);
//			while (rs.next()) {
//				String temp = rs.getString("ID").substring(0, 5);
//				if (target.equalsIgnoreCase(temp))
//					ID_MAX = Math.max(
//							ID_MAX,
//							Integer.parseInt(rs.getString("formID").substring(
//									5)));// 最后5位编号
//			}
//		} catch (SQLException e) {
//			System.err.println("访问数据库时出错：");
//			e.printStackTrace();
//		}
//
//		ID_MAX++;// 将该数字加一
//		if (ID_MAX > 99999)
//			return null;
//		String added = String.format("%05d", ID_MAX);
//
//		return target + added;
//	}

}
