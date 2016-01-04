package bl.blImpl.searchbl;

import java.rmi.RemoteException;


import java.util.List;
import java.util.NoSuchElementException;

import po.configurationdata.City2DPO;
import po.deliverdata.DeliverPO;
import po.financedata.RevenuePO;
import po.receivedata.ReceivePO;
import po.transportdata.CenterOutPO;
import po.transportdata.LoadPO;
import bl.NetReconnect.Reconnect;
import bl.blImpl.transportbl.TransportHallBLImpl;
import bl.clientNetCache.CacheHelper;
import rmi.configurationdata.ConfigurationDataService;
import rmi.deliverdata.DeliverDataService;
import rmi.financedata.RevenueDataService;
import rmi.receivedata.ReceiveDataService;
import rmi.transportdata.CenterOutDataService;
import rmi.transportdata.LoadDataService;
import tool.time.TimeConvert;
import vo.configurationvo.City2DVO;

/**
 * Client//bl.blImpl.searchbl//Intergrate.java
 * @author CXWorks
 * @date 2015年11月15日 下午5:12:52
 * @version 1.0
 */
public class Intergrate {
	private List<City2DPO> city2dvos;
	public Intergrate(){
		
	}
	
	public String[] search(String ID) throws RemoteException{
		String[] ans=new String[2];
		String index=ID.substring(0, 2);
		switch (index) {
		case "02":
			RevenueDataService revenueDataService=CacheHelper.getRevenueDataService();
			RevenuePO revenuePO=revenueDataService.getFormPO(ID);
			ans[0]=this.transID2Name(revenuePO.getHallID().substring(0, 3))+" 收款单";
			ans[1]=TimeConvert.getDisplayDate(revenuePO.getDate());
			break;
		case "08":
			LoadDataService loadDataService=CacheHelper.getLoadDataService();
			LoadPO loadPO=loadDataService.getFormPO(ID);
			ans[0]=this.transID2Name(loadPO.getPlaceTo().substring(0, 3))+" 装车单";
			ans[1]=TimeConvert.getDisplayDate(loadPO.getLoadDate());
			break;
		case "07":
			CenterOutDataService centerOutDataService=CacheHelper.getTransportDataService();
			CenterOutPO centerOutPO=centerOutDataService.getFormPO(ID);
			ans[0]=this.transID2Name(centerOutPO.getPlaceTo().substring(0, 3))+" 中转单";
			ans[1]=TimeConvert.getDisplayDate(centerOutPO.getLoadDate());
			break;
		case "04":
			DeliverDataService deliverDataService=CacheHelper.getDeliverDataService();
			DeliverPO deliverPO=deliverDataService.getFormPO(ID);
			ans[0]="收件人："+deliverPO.getReceivePeople();
			ans[1]=TimeConvert.getDisplayDate(deliverPO.getDate());
			break;
		case "03":
			ReceiveDataService receiveDataService=CacheHelper.getReceiveDataService();
			ReceivePO receivePO=receiveDataService.getFormPO(ID);
			ans[0]=(receivePO.getDepature().contains("0"))?(this.transID2Name(receivePO.getDepature().substring(0, 3))):(receivePO.getDepature())+" 接收单";
			ans[1]=TimeConvert.getDisplayDate(receivePO.getDate());
			break;

		default:
			ans=null;
			break;
		}
		//
		return ans;
	}
	//
	private String transID2Name(String id){
		
		if (this.city2dvos==null) {
			ConfigurationDataService configurationDataService=CacheHelper.getConfigurationDataService();
			try {
				city2dvos=configurationDataService.getAllCity2D();
			} catch (RemoteException e) {
				Reconnect.ReConnectFactory();
			}
		}
		try{
		String ans=city2dvos.stream().filter(city->city.getID().equalsIgnoreCase(id)).findFirst().get().getName();
		return ans;
		}
		catch(NoSuchElementException e){
			return id;
		}
		
	}
}
