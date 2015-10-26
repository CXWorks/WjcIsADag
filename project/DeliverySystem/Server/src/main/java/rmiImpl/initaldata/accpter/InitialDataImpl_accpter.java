package rmiImpl.initaldata.accpter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.initialdata.InitialDataService;
import rmiImpl.initaldata.InitialDataImpl;

public class InitialDataImpl_accpter {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				try {
					LocateRegistry.createRegistry(2333);
					InitialDataService InitialDataServiceImpl=new InitialDataImpl();
					Naming.rebind("rmi://localhost:2333/InitialDataService", InitialDataServiceImpl);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}
}
