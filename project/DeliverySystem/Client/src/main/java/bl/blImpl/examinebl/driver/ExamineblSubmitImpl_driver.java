package bl.blImpl.examinebl.driver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import rmi.examineService.ExamineSubmitService;
import message.OperationMessage;
import bl.blImpl.examinebl.ExamineBLSubmitImpl;
import bl.blService.examineblService.ExamineblSubmitService;

/** 
 * Client//blImpl.examinebl.driver//ExamineblSubmitImpl_driver.java
 * @author CXWorks
 * @date 2015年10月26日 上午9:07:16
 * @version 1.0 
 */
public class ExamineblSubmitImpl_driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ExamineSubmitService examineSubmitService=(ExamineSubmitService)Naming.lookup("rmi://localhost:2333/examineSubmitService");
			System.out.println(examineSubmitService.submit(null) instanceof OperationMessage);
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
