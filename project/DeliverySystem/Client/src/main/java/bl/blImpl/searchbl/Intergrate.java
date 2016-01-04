package bl.blImpl.searchbl;

import java.rmi.RemoteException;

import po.deliverdata.DeliverPO;
import po.financedata.RevenuePO;
import po.receivedata.ReceivePO;
import po.transportdata.CenterOutPO;
import po.transportdata.LoadPO;
import bl.blImpl.transportbl.TransportHallBLImpl;
import bl.clientNetCache.CacheHelper;
import rmi.deliverdata.DeliverDataService;
import rmi.financedata.RevenueDataService;
import rmi.receivedata.ReceiveDataService;
import rmi.transportdata.CenterOutDataService;
import rmi.transportdata.LoadDataService;
import tool.time.TimeConvert;

/**
 * Client//bl.blImpl.searchbl//Intergrate.java
 * @author CXWorks
 * @date 2015年11月15日 下午5:12:52
 * @version 1.0
 */
public class Intergrate {
	public String[] search(String ID) throws RemoteException{
		String[] ans=new String[2];
		String index=ID.substring(0, 2);
		switch (index) {
		case "02":
			RevenueDataService revenueDataService=CacheHelper.getRevenueDataService();
			RevenuePO revenuePO=revenueDataService.getFormPO(ID);
			ans[0]=revenuePO.getHallID();
			ans[1]=TimeConvert.getDisplayDate(revenuePO.getDate());
			break;
		case "08":
			LoadDataService loadDataService=CacheHelper.getLoadDataService();
			LoadPO loadPO=loadDataService.getFormPO(ID);
			ans[0]=loadPO.getPlaceTo();
			ans[1]=TimeConvert.getDisplayDate(loadPO.getLoadDate());
			break;
		case "07":
			CenterOutDataService centerOutDataService=CacheHelper.getTransportDataService();
			CenterOutPO centerOutPO=centerOutDataService.getFormPO(ID);
			ans[0]=centerOutPO.getPlaceTo();
			ans[1]=TimeConvert.getDisplayDate(centerOutPO.getLoadDate());
			break;
		case "04":
			DeliverDataService deliverDataService=CacheHelper.getDeliverDataService();
			DeliverPO deliverPO=deliverDataService.getFormPO(ID);
			ans[0]=deliverPO.getFormID().substring(2, 9);
			ans[1]=TimeConvert.getDisplayDate(deliverPO.getDate());
			break;
		case "03":
			ReceiveDataService receiveDataService=CacheHelper.getReceiveDataService();
			ReceivePO receivePO=receiveDataService.getFormPO(ID);
			ans[0]=receivePO.getDepature();
			ans[1]=TimeConvert.getDisplayDate(receivePO.getDate());
			break;

		default:
			ans=null;
			break;
		}
		//
		return ans;
	}
}
