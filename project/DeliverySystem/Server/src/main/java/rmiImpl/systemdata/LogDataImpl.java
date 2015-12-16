package rmiImpl.systemdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import message.OperationMessage;
import po.systemdata.LogPO;
import rmi.systemdata.LogDataService;
import database.ConnecterHelper;

/**
 * Created by WJC on 2015/11/29.
 */
public class LogDataImpl extends UnicastRemoteObject implements LogDataService {

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;
	private static int ID;

	// public static void main(String[] args) throws RemoteException {
	// LogDataImpl t = new LogDataImpl();
	// t.clear();
	// }

	public LogDataImpl() throws RemoteException {
		super();
		Table_Name = "log";
		conn = ConnecterHelper.getConn();
		ID = this.getMaxID();
	}

	@Override
	public Connection getConn() throws RemoteException {
		return conn;
	}

	public int getMaxID() throws RemoteException {
		String selectAll = "select * from `" + Table_Name + "`";
		ResultSet rs = null;
		int ID_MAX = 0;
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				ID_MAX = Math.max(ID_MAX, rs.getInt("ID"));
			}
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}

		return ID_MAX;
	}

	@Override
	public OperationMessage insert(LogPO po) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String insert = "insert into `" + Table_Name + "`(ID,personID,time,info) " + "values('" + ++ID + "','"
				+ po.getPersonID() + "','" + po.getTimeForSQL() + "','" + po.getInfo() + "')";

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

	public static void main(String[] args) throws RemoteException {
		LogDataImpl t = new LogDataImpl();
		Calendar a = Calendar.getInstance();
		Calendar b = Calendar.getInstance();
		a.set(Calendar.YEAR, 2014);
		b.set(Calendar.YEAR, 2015);
		System.out.println(t.getByTime(a,b).size());
	}

	@Override
	public ArrayList<LogPO> getByTime(Calendar start, Calendar end) throws RemoteException {
		String select = "select * from `" + Table_Name + "` where '" + start.getTime().getTime() / 1000
				+ "' < UNIX_TIMESTAMP(`time`) " + "and '" + end.getTime().getTime() / 1000
				+ "' > UNIX_TIMESTAMP(`time`)";

		ResultSet rs = null;
		LogPO log = null;
		ArrayList<LogPO> result = new ArrayList<LogPO>();
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) {
				log = new LogPO(rs.getString("personID"), rs.getTimestamp("time"), rs.getString("info"));
				result.add(log);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
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

}
