package rmiImpl.companydata.accpter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.companydata.CompanyDataCenterService;
import rmiImpl.companydata.CompanyDataCenterImpl;

/** 
 * Server//rmiImpl.configurationdata.accpter//CompanyDataCenterImpl_accpter.java
 * @author CXWorks
 * @date 2015年10月26日 上午10:09:01
 * @version 1.0 
 */
public class CompanyDataCenterImpl_accpter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			LocateRegistry.createRegistry(2333);
			CompanyDataCenterService companyDataCenterService=new CompanyDataCenterImpl();
			Naming.rebind("rmi://localhost:2333/CompanyDataCenterService", companyDataCenterService);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
