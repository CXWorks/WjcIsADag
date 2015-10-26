package rmiImpl.transportdata.accpter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


import rmi.transportdata.TransportDataService;

import rmiImpl.transportdata.TransportDataImpl;

public class TransportDataImpl_accpter {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			LocateRegistry.createRegistry(2333);
			TransportDataService TransportDataServiceImpl=new TransportDataImpl();
			Naming.rebind("rmi://localhost:2333/TransportDataService", TransportDataServiceImpl);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
