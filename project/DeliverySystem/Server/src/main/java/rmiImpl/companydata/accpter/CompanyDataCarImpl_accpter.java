package rmiImpl.companydata.accpter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.companydata.CompanyDataCarService;
import rmiImpl.companydata.CompanyDataCarImpl;

/** 
 * Server//rmiImpl.companydata.accpter//CompanyDataCarImpl_accpter.java
 * @author CXWorks
 * @date 2015年10月26日 上午10:05:09
 * @version 1.0 
 */
public class CompanyDataCarImpl_accpter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			LocateRegistry.createRegistry(2333);
			CompanyDataCarService companyDataCarService=new CompanyDataCarImpl();
			Naming.rebind("rmi://localhost:2333/companyDataService", companyDataCarService);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
