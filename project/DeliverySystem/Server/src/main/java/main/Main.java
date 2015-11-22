package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rmi.accountdata.AccountDataService;
import rmi.receivedata.ReceiveDataService;
import rmiImpl.ConnecterHelper;
import rmiImpl.DataFactory;
import rmiImpl.accountdata.AccountDataImpl;
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
			LocateRegistry.createRegistry(2336);
			ReceiveDataService test = new ReceiveDataImpl();
			Naming.rebind("rmi://localhost:2336/ReceiveDataService", test);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			ReceiveDataImpl test = new ReceiveDataImpl();
//			
////			ReceivePO t1 = test.getFormPO("031234567201511220000001");
////			t1.setFormID("111");
//			test.delete("111");
//			ConnecterHelper.deconnSQL(test.getConn());
//			System.exit(0);
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
