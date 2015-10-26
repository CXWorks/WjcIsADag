package rmiImpl.orderdata.accpter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


import rmi.orderdata.OrderDataService;

import rmiImpl.orderdata.OrderDataImpl;

public class OrderDataImpl_accpter {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			LocateRegistry.createRegistry(2333);
			OrderDataService OrderDataServiceImpl=new OrderDataImpl();
			Naming.rebind("rmi://localhost:2333/OrderDataService", OrderDataServiceImpl);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
