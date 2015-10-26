package rmiImpl.examineImpl.accepter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import rmi.examineService.ExamineManageService;
import rmiImpl.examineImpl.ExamineManageImpl;

/** 
 * Server//rmiImpl.examineImpl.accepter//ExamineManageImpl_accepter.java
 * @author CXWorks
 * @date 2015年10月26日 上午8:54:31
 * @version 1.0 
 */
public class ExamineManageImpl_accepter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			LocateRegistry.createRegistry(2333);
			ExamineManageService examineManageServiceImpl=new ExamineManageImpl();
			Naming.rebind("rmi://localhost:2333/examineManageService", examineManageServiceImpl);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
