package rmiImpl.storedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

import database.ConnecterHelper;
import database.MySql;
import database.enums.TableEnum;
import message.OperationMessage;
import rmi.storedata.TackDataService;

/**
 * Created by WJC on 2015/12/24.
 */
public class TackDataImpl extends UnicastRemoteObject implements TackDataService {

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "TackData";

	private Connection conn = null;
	private PreparedStatement statement = null;

	public TackDataImpl() throws RemoteException {
		super();
		conn = ConnecterHelper.getConn();
	}

	@Override
	public Connection getConn() throws RemoteException {
		return conn;
	}

	@Override
	public int getTack(String centerID) throws RemoteException {
		ResultSet rs = null;
		String select = MySql.select(TableEnum.TACK, new HashMap<String, String>() {
			{
				put("centerID", centerID);
			}
		});
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			return Integer.parseInt(rs.getString("num"));
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public OperationMessage setTack(String centerID, String num) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String update = MySql.update(TableEnum.TACK, "num", num, new HashMap<String, String>() {
			{
				put("centerID", "centerID");
			}
		});
		try {
			statement = conn.prepareStatement(update);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "更新时出错：");
			System.err.println("设置序号时出错：");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int newTackNum(String centerID) throws RemoteException {
		ResultSet rs = null;
		Timestamp date = null;
		String select = MySql.select(TableEnum.TACK, new HashMap<String, String>() {
			{
				put("centerID", centerID);
			}
		});
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			date = rs.getTimestamp("date");
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}
		String date_in_sql = date.toString().substring(0, 10);
		date_in_sql = date_in_sql.substring(0, 4) + date_in_sql.substring(5, 7) + date_in_sql.substring(8);
		String temp = new Timestamp(System.currentTimeMillis()).toString().substring(0, 10);
		String today = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8);

		// update date
		String update = MySql.update(TableEnum.TACK, "date", new Timestamp(System.currentTimeMillis()).toString(),
				new HashMap<String, String>() {
					{
						put("centerID", "centerID");
					}
				});
		try {
			statement = conn.prepareStatement(update);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("更新日期时出错：");
			e.printStackTrace();
		}

		// 当前日期与缓存日期一致
		if (date_in_sql.equalsIgnoreCase(today)) {
			int num = 0;
			try {
				num = Integer.parseInt(rs.getString("num"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return num++;
		} else {// 当前日期与缓存日期不一致
			return 1;
		}
	}
}
