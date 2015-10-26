package blImpl.manangrbl.driver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import rmi.companydata.CompanyDataCarService;
import rmi.configurationdata.ConfigurationDataService;

/** 
 * Client//blImpl.manangrbl.driver//ManageblCarImpl_driver.java
 * @author CXWorks
 * @date 2015年10月26日 上午9:37:12
 * @version 1.0 
 */
public class ManageblCarImpl_driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			CompanyDataCarService companyDataCarService=(CompanyDataCarService)Naming.lookup("rmi://localhost:2333/companyDataCarService");
			System.out.println(companyDataCarService.getCar() instanceof ArrayList<?>);
			System.out.println(companyDataCarService.addCar(null) instanceof OperationMessage);
			System.out.println(companyDataCarService.modifyCar(null) instanceof OperationMessage);
			System.out.println(companyDataCarService.deleteCar(null) instanceof OperationMessage);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
