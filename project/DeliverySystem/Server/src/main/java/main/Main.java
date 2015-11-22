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
import java.util.Calendar;

import po.receivedata.ReceivePO;

public class Main {

	/**
	 * 主函数
	 * 
	 * @author wjc
	 * @version 2015.11.18
	 */
	public static void main(String args[]) throws SQLException {
		try {
			ReceiveDataImpl test = new ReceiveDataImpl();
			ReceivePO t1 = test.getFormPO("031234567201511220000001");
			System.out.println(test.newID("1234567"));
			ConnecterHelper.deconnSQL(test.getConn());
			System.exit(0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
