package blImpl.manangrbl.driver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import rmi.companydata.CompanyDataCenterService;


/** 
 * Client//blImpl.manangrbl.driver//ManageblCenterImpl_driver.java
 * @author CXWorks
 * @date 2015年10月26日 上午9:42:51
 * @version 1.0 
 */
public class ManageblCenterImpl_driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			CompanyDataCenterService companyDataCenterService=(CompanyDataCenterService)Naming.lookup("rmi://localhost:2333/CompanyDataCenterService");
			System.out.println(companyDataCenterService.getCenter() instanceof ArrayList<?>);
			System.out.println(companyDataCenterService.addCenter(null) instanceof OperationMessage);
			System.out.println(companyDataCenterService.modifyCenter(null) instanceof OperationMessage);
			System.out.println(companyDataCenterService.deleteCenter(null) instanceof OperationMessage);
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
