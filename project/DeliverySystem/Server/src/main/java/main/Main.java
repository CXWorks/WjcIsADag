package main;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rmiImpl.ConnecterHelper;
import rmiImpl.DataFactory;
import rmiImpl.receivedata.ReceiveDataImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	
	private Connection conn = null;
	PreparedStatement statement = null;

	// execute insertion language
	boolean insertSQL(String sql) {
		try {
			statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("插入数据库时出错：");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("插入时出错：");
			e.printStackTrace();
		}
		return false;
	}

	// execute delete language
	boolean deleteSQL(String sql) {
		try {
			statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("插入数据库时出错：");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("插入时出错：");
			e.printStackTrace();
		}
		return false;
	}

	// execute update language
	boolean updateSQL(String sql) {
		try {
			statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("插入数据库时出错：");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("插入时出错：");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 主函数
	 * @author wjc
	 * @version 2015.11.18
	 */
	public static void main(String args[]) throws SQLException {
		try {
			ReceiveDataImpl test = new ReceiveDataImpl();
			test.getAll();
			ConnecterHelper.deconnSQL(test.getConn());
			System.exit(0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String insert = "insert into ju_users(ju_userID,TaobaoID,ju_userName,ju_userPWD) values("
//				+ 8329 + "," + 34243 + ",'mm','789')";
//		String update = "update ju_users set ju_userPWD =123 where ju_userName= 'mm'";
//		String delete = "delete from ju_users where ju_userName= 'mm'";
//
//		if (h.insertSQL(insert) == true) {
//			System.out.println("insert successfully");
//			ResultSet resultSet = h.selectSQL(s);
//			h.layoutStyle2(resultSet);
//		}
//		if (h.updateSQL(update) == true) {
//			System.out.println("update successfully");
//			ResultSet resultSet = h.selectSQL(s);
//			h.layoutStyle2(resultSet);
//		}
//		if (h.insertSQL(delete) == true) {
//			System.out.println("delete successfully");
//			ResultSet resultSet = h.selectSQL(s);
//			h.layoutStyle2(resultSet);
//		}
	}

}
