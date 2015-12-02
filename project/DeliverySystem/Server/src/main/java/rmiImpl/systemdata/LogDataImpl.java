package rmiImpl.systemdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import message.OperationMessage;
import po.FormPO;
import po.storedata.StoreInPO;
import po.storedata.StoreOutPO;
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
	private int ID;

	public LogDataImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		Table_Name = "log";
		conn = ConnecterHelper.connSQL(conn);
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return conn;
	}

	public int getMaxID() throws RemoteException {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String insert = "insert into `" + Table_Name
				+ "`(ID,personID,time,info) " + "values('" + ++ID + "','"
				+ po.getPersonID() + "','" + po.getTimeForSQL() + "','"
				+ po.getInfo() + "')";

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
	public ArrayList<LogPO> getByTime(Calendar start, Calendar end)
			throws RemoteException {
		// TODO Auto-generated method stub
		Timestamp start_t = new Timestamp(start.getTimeInMillis());
		Timestamp end_t = new Timestamp(end.getTimeInMillis());
		String selectIn = "select * from `" + Table_Name + "`";
		ResultSet rs = null;
		LogPO log = null;
		ArrayList<LogPO> result = new ArrayList<LogPO>();
		Timestamp tmp = null;
		try {
			statement = conn.prepareStatement(selectIn);
			rs = statement.executeQuery(selectIn);
			while (rs.next()) {
				tmp = rs.getTimestamp("time");
				if (!(tmp.getTime() > start_t.getTime() || tmp.getTime() < end_t
						.getTime()))
					continue;
				log = new LogPO(rs.getString("personID"),
						tmp,rs.getString("info"));
				result.add(log);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

}
