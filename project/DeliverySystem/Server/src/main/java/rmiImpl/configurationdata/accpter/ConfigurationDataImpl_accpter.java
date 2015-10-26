package rmiImpl.configurationdata.accpter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.configurationdata.ConfigurationDataService;
import rmiImpl.configurationdata.ConfigurationDataImpl;

/** 
 * Server//rmiImpl.configurationdata.accpter//ConfigurationDataImpl_accpter.java
 * @author CXWorks
 * @date 2015年10月26日 上午9:28:09
 * @version 1.0 
 */
public class ConfigurationDataImpl_accpter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			LocateRegistry.createRegistry(2333);
			ConfigurationDataService configurationDataService=new ConfigurationDataImpl();
			Naming.rebind("rmi://localhost:2333/configurationDataService", configurationDataService);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
