package rmiImpl.receivedata.accpter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.receivedata.ReceiveDataService;
import rmiImpl.receivedata.ReceiveDataImpl;



/**
 * 
 * @author wjc
 *2015/10/26
 */
public class ReceiveDataImpl_accpter {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			LocateRegistry.createRegistry(2333);
			ReceiveDataService ReceiveDataServiceImpl=new ReceiveDataImpl();
			Naming.rebind("rmi://localhost:2333/ReceiveDataService", ReceiveDataServiceImpl);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
