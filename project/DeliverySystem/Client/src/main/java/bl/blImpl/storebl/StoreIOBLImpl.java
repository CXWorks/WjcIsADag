package bl.blImpl.storebl;

import bl.NetReconnect.Reconnect;
import bl.blService.storeblService.StoreIOBLService;
import bl.clientNetCache.CacheHelper;
import message.CheckFormMessage;
import tool.vopo.VOPOFactory;
import userinfo.UserInfo;
import vo.ordervo.OrderVO;
import vo.storevo.GoodsVO;
import vo.storevo.StoreFormVO;
import vo.storevo.StoreInVO;
import vo.storevo.StoreOutVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import po.FormPO;
import po.orderdata.OrderPO;
import po.storedata.StoreInPO;
import po.storedata.StoreOutPO;
import rmi.orderdata.OrderDataService;
import rmi.storedata.StoreFormDataService;

/**
 * Created by Sissel on 2015/10/26.
 */
public class StoreIOBLImpl implements StoreIOBLService {
	private VOPOFactory vopoFactory;
	public StoreIOBLImpl(VOPOFactory vopoFactory){
		this.vopoFactory=vopoFactory;

	}
    public List<StoreFormVO> getGoodsInfo(Calendar startDate,Calendar endDate) {
    	StoreFormDataService storeFormDataService=CacheHelper.getStoreFormDataService();

        try {
			List<FormPO> storeInPOs=storeFormDataService.getInOutInfo(UserInfo.getInstitutionID(), startDate, endDate);

			//
			List<StoreFormVO> answer=(List<StoreFormVO>) vopoFactory.transPOtoVO(storeInPOs);
			return answer;
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return null;
		}
    }

    public List<StoreFormVO> filterGoods(String orderNumber) {
        LinkedList<StoreFormVO> answer=new LinkedList<StoreFormVO>();
        StoreFormDataService storeFormDataService=CacheHelper.getStoreFormDataService();
		try {
			StoreInPO storeInPO=storeFormDataService.getStoreInPO(orderNumber);
	        StoreOutPO storeOutPO=storeFormDataService.getStoreOutPO(orderNumber);
	        if (storeInPO!=null) {
	        	StoreInVO storeInVO=(StoreInVO)vopoFactory.transPOtoVO(storeInPO);
	        	answer.add(storeInVO);
			}
	        if (storeOutPO!=null) {
	        	StoreOutVO storeOutVO=(StoreOutVO)vopoFactory.transPOtoVO(storeOutPO);
	        	answer.add(storeOutVO);
			}


	        return answer;
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return null;
		}

    }

    public List<CheckFormMessage> checkFormat(String inDate, String inTime, String outDate, String outTime) {
    	CheckFormMessage tmp = new CheckFormMessage();
    	LinkedList<CheckFormMessage> result = new LinkedList<CheckFormMessage>();
    	result.add(tmp);
    	return result;
    }
    //
    private boolean comp(Calendar strat,Calendar end,Calendar target){
		if(target.after(strat)&&target.before(end))
			return true;
		return false;
	}
}
