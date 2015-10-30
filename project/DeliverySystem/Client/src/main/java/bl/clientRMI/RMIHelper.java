package bl.clientRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import rmi.orderdata.OrderDataService;

/** 
 * Client//bl.clientRMI//RMIHelper.java
 * @author CXWorks
 * @date 2015年10月30日 下午5:13:14
 * @version 1.0 
 */
public class RMIHelper {
	private static final String IP="localhost";
	
	private static boolean initialized=false;
	
	private static OrderDataService orderDataService;
	
	public synchronized static void	init() throws NetInitException {
		if(initialized)
			return;
		try {
			initDataService();
		} catch (Exception e) {
			throw new NetInitException(e);
		}
	}
	//
	private static void initDataService() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+IP+"/";
		orderDataService=(OrderDataService)Naming.lookup(url+OrderDataService.class.getName());
	}
	//
	public static OrderDataService getOrderDataService(){
		return orderDataService;
	}
}
