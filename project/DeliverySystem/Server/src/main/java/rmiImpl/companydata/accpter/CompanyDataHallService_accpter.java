package rmiImpl.companydata.accpter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.companydata.CompanyDataHallService;
import rmiImpl.companydata.CompanyDataHallImpl;

/** 
 * Server//rmiImpl.configurationdata.accpter//CompanyDataHallService.java
 * @author CXWorks
 * @date 2015年10月26日 上午10:10:41
 * @version 1.0 
 */
public class CompanyDataHallService_accpter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			LocateRegistry.createRegistry(2333);
			CompanyDataHallService companyDataHallService=new CompanyDataHallImpl();
			Naming.rebind("rmi://localhost:2333/companyDataHallService", companyDataHallService);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
