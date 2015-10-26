package rmiImpl.memberdata.accpter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.memberdata.MemberDataService;
import rmiImpl.memberdata.MemberDataImpl;

/** 
 * Server//rmiImpl.memberdata.accpter//MemberDataImpl_accpter.java
 * @author CXWorks
 * @date 2015年10月26日 上午10:17:09
 * @version 1.0 
 */
public class MemberDataImpl_accpter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			LocateRegistry.createRegistry(2333);
			MemberDataService memberDataService=new MemberDataImpl();
			Naming.rebind("rmi://localhost:2333/memberDataService", memberDataService);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
