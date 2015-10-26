package blImpl.manangrbl.driver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import rmi.companydata.CompanyDataHallService;

/** 
 * Client//blImpl.manangrbl.driver//ManageblHallImpl_driver.java
 * @author CXWorks
 * @date 2015年10月26日 上午9:51:51
 * @version 1.0 
 */
public class ManageblHallImpl_driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			CompanyDataHallService companyDataHallService=(CompanyDataHallService)Naming.lookup("rmi://localhost:2333/companyDataHallService");
			System.out.println(companyDataHallService.getHall() instanceof ArrayList<?>);
			System.out.println(companyDataHallService.newHallID("111") instanceof String);
			System.out.println(companyDataHallService.addHall(null) instanceof OperationMessage);
			System.out.println(companyDataHallService.modifyHall(null) instanceof OperationMessage);
			System.out.println(companyDataHallService.deleteHall(null) instanceof OperationMessage);
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
