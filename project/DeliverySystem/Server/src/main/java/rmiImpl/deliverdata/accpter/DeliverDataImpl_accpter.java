package rmiImpl.deliverdata.accpter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.deliverdata.DeliverDataService;
import rmiImpl.deliverdata.*;

/**
 * 
 * @author wjc
 *2015/10/26
 */
public class DeliverDataImpl_accpter {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				try {
					LocateRegistry.createRegistry(2333);
					DeliverDataService DeliverDataServiceImpl=new DeliverDataImpl();
					Naming.rebind("rmi://localhost:2333/DeliverDataService", DeliverDataServiceImpl);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}
}
