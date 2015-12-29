package bl.blImpl.searchbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import bl.NetReconnect.Reconnect;
import bl.clientNetCache.CacheHelper;
import po.orderdata.OrderPO;
import rmi.orderdata.OrderDataService;
import vo.logisticsvo.LogisticsVO;

/** 
 * Client//bl.blImpl.searchbl//OrderHelper.java
 * @author CXWorks
 * @date 2015年11月15日 下午5:12:30
 * @version 1.0 
 */
public class OrderHelper {
	private OrderPO toSearch;
	private Intergrate intergrate;
	public OrderHelper(){
		
		intergrate=new Intergrate();
	}
	
	public LogisticsVO searchOrder(String orderID){
		OrderDataService orderDataService=CacheHelper.getOrderDataService();
		try {
			this.toSearch=orderDataService.getFormPO(orderID);
			//
			ArrayList<String> formID=toSearch.getFormIDs();
			ArrayList<String> location=new ArrayList<String>(formID.size());
			ArrayList<String> time=new ArrayList<String>(formID.size());
			for (int i = 0; i < formID.size(); i++) {
				String each=formID.get(i);
				String[] ans=intergrate.search(each);
				if (ans!=null) {
					location.add(ans[0]);
					time.add(ans[1]);
				} 
			}
			//
			return new LogisticsVO(toSearch, location, time);
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return null;
		}
	}
}
