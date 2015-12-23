package rmiImpl.accountdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import database.ConnecterHelper;
import database.MySql;
import database.enums.TableEnum;
import message.OperationMessage;
import po.accountdata.AccountPO;
import rmi.accountdata.AccountDataService;

/**
 *
 * @author mx 2015/10/25
 */

public class AccountDataImpl extends UnicastRemoteObject implements AccountDataService {

	private Connection conn = null;
	private PreparedStatement statement = null;

	public AccountDataImpl() throws RemoteException {
		super();
		conn = ConnecterHelper.getConn();
	}

	public Connection getConn() {
		return conn;
	}

	public AccountPO getAccountPO(String accountID) {
		String select = MySql.select(TableEnum.ACCOUNT, new HashMap<String, String>() {
			{
				put("ID", accountID);
			}
		});
		ResultSet rs = null;
		AccountPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new AccountPO(rs.getString("ID"), rs.getString("authority"), rs.getString("password"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public ArrayList<AccountPO> getAccountPOs() {
		String selectAll = MySql.select(TableEnum.ACCOUNT, null);
		ResultSet rs = null;
		AccountPO temp = null;
		ArrayList<AccountPO> result = new ArrayList<AccountPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new AccountPO(rs.getString("ID"), rs.getString("authority"), rs.getString("password"));
				result.add(temp);

			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage insert(AccountPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String insert = MySql.insert(TableEnum.ACCOUNT, new HashMap<String, String>() {
			{
				put("ID", po.getID());
				put("password", po.getPassword());
				put("authority", po.getAuthority().toString());
				put("online", "0");
			}
		});
		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "新建时出错：");
			System.err.println("新建时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage delete(String accountID) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String delete = MySql.delete(TableEnum.ACCOUNT, new HashMap<String, String>() {
			{
				put("ID", accountID);
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

	public OperationMessage update(AccountPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		if (!this.delete(po.getID()).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应账号");
		if (!this.insert(po).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;

	}

	public OperationMessage checkAccount(String id, String password) {
		OperationMessage result = new OperationMessage();
		AccountPO a = this.getAccountPO(id);
		if (a == null)
			result = new OperationMessage(false, "账户不存在");
		else if (!password.equalsIgnoreCase(a.getPassword()))
			result = new OperationMessage(false, "账户密码不匹配");
		return result;
	}

	@Override
	public OperationMessage setAccount(String id, boolean isOnline) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String online = "0";
		if (isOnline) {
			online = "1";
		}
		String operation = MySql.update(TableEnum.ACCOUNT, "online", online, new HashMap<String, String>() {
			{
				put("ID", id);
			}
		});
		try {
			statement = conn.prepareStatement(operation);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "更新账户状态出错：");
			System.err.println("更新账户状态出错：");
			e.printStackTrace();
		}
		return result;
	}

	// public String newAccountID(String type,String city) {
	// String selectAll = "select * from " + Table_Name;
	// ResultSet rs = null;
	// int ID_MAX = 0;
	// String target = type + city;// 工种+城市
	// try {+
	// statement = conn.prepareStatement(selectAll);
	// rs = statement.executeQuery(selectAll);
	// while (rs.next()) {
	// String temp = rs.getString("ID").substring(0, 5);
	// if (target.equalsIgnoreCase(temp))
	// ID_MAX = Math.max(
	// ID_MAX,
	// Integer.parseInt(rs.getString("formID").substring(
	// 5)));// 最后5位编号
	// }
	// } catch (SQLException e) {
	// System.err.println("访问数据库时出错：");
	// e.printStackTrace();
	// }
	//
	// ID_MAX++;// 将该数字加一
	// if (ID_MAX > 99999)
	// return null;
	// String added = String.format("%05d", ID_MAX);
	//
	// return target + added;
	// }

}
