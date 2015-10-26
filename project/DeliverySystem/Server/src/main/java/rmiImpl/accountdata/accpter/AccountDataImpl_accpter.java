package rmiImpl.accountdata.accpter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.accountdata.AccountDataService;

import rmiImpl.accountdata.AccountDataImpl;


public class AccountDataImpl_accpter {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			LocateRegistry.createRegistry(2333);
			AccountDataService AccountDataServiceImpl=new AccountDataImpl();
			Naming.rebind("rmi://localhost:2333/AccountDataService", AccountDataServiceImpl);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
