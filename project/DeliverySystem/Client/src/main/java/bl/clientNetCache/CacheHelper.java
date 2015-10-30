package bl.clientNetCache;

import bl.clientRMI.RMIHelper;

import com.sun.jndi.url.rmi.rmiURLContext;
import com.sun.jndi.url.rmi.rmiURLContextFactory;

import rmi.orderdata.OrderDataService;
import rmi.transportdata.TransportDataService;

/** 
 * Client//bl.clientNetCache//CacheHelper.java
 * @author CXWorks
 * @date 2015年10月30日 下午7:16:58
 * @version 1.0 
 */
public class CacheHelper {
	private static OrderDataService orderDataService;
	private static TransportDataService transportDataService;
	//
	public static void  initializeCache(){
		if(initStoredData()){
			updateCache();
		}
		initCacheService();
	}
	//
	private static boolean initStoredData(){
		return true;
	}
	//
	private static void updateCache(){
		
	}
	//
	private static void initCacheService(){
		orderDataService=RMIHelper.getOrderDataService();
	}
	//
	public static OrderDataService getOrderDataService() {
		return orderDataService;
	}
	
}
