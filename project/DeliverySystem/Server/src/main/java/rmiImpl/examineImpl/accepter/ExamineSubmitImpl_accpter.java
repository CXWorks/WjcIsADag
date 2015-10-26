package rmiImpl.examineImpl.accepter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.examineService.ExamineSubmitService;
import rmiImpl.examineImpl.ExamineSubmitImpl;

/** 
 * Server//rmiImpl.examineImpl.accepter//ExamineSubmitImpl_accpter.java
 * @author CXWorks
 * @date 2015年10月26日 上午9:12:26
 * @version 1.0 
 */
public class ExamineSubmitImpl_accpter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			LocateRegistry.createRegistry(2333);
			ExamineSubmitService examineSubmitService=new ExamineSubmitImpl();
			Naming.rebind("rmi://localhost:2333/examineSubmitService", examineSubmitService);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
