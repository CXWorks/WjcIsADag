package blImpl.manangrbl.driver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import rmi.memberdata.MemberDataService;

/** 
 * Client//blImpl.manangrbl.driver//ManageblStaffImpl_driver.java
 * @author CXWorks
 * @date 2015年10月26日 上午9:54:48
 * @version 1.0 
 */
public class ManageblStaffImpl_driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			MemberDataService memberDataService=(MemberDataService)Naming.lookup("rmi://localhost:2333/memberDataService");
			System.out.println(memberDataService.getStaff(null) instanceof ArrayList<?>);
			System.out.println(memberDataService.newStaffID(null) instanceof String);
			System.out.println(memberDataService.addStaff(null) instanceof OperationMessage);
			System.out.println(memberDataService.modifyStaff(null) instanceof OperationMessage);
			System.out.println(memberDataService.dismissStaff(null) instanceof OperationMessage);
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
