package bl.blImpl.examinebl.driver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.FormPO;
import rmi.examineService.ExamineManageService;

/** 
 * Client//blImpl.examinebl.driver//ExamineblManageImpl_driver.java
 * @author CXWorks
 * @date 2015年10月26日 上午8:44:11
 * @version 1.0 
 */
public class ExamineblManageImpl_driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//创建的是data层的接口
			ExamineManageService examineManageService=(ExamineManageService)Naming.lookup("rmi://localhost:2333/examineManageService");
			System.out.println(examineManageService.getForms() instanceof ArrayList<?>);
			System.out.println(examineManageService.modifyForm(new FormPO()) instanceof OperationMessage);
			System.out.println(examineManageService.passForm(null) instanceof OperationMessage);
			System.out.println(examineManageService.deleteForm(null) instanceof OperationMessage);
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
