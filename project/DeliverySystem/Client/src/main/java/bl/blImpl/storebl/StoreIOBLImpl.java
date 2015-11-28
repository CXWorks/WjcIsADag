package bl.blImpl.storebl;

import bl.blService.storeblService.StoreIOBLService;
import bl.clientNetCache.CacheHelper;
import message.CheckFormMessage;
import tool.vopo.VOPOFactory;
import vo.ordervo.OrderVO;
import vo.storevo.GoodsVO;
import vo.storevo.StoreInVO;
import vo.storevo.StoreOutVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import po.orderdata.OrderPO;
import po.storedata.StoreInPO;
import po.storedata.StoreOutPO;
import rmi.orderdata.OrderDataService;
import rmi.storedata.StoreFormDataService;

/**
 * Created by Sissel on 2015/10/26.
 */
public class StoreIOBLImpl implements StoreIOBLService {
	private OrderDataService orderDataService;
	private StoreFormDataService storeFormDataService;
	private VOPOFactory vopoFactory;
	public StoreIOBLImpl(VOPOFactory vopoFactory){
		this.vopoFactory=vopoFactory;
		this.orderDataService=CacheHelper.getOrderDataService();
		this.storeFormDataService=CacheHelper.getStoreFormDataService();
	}
    public List<GoodsVO> getGoodsInfo(Calendar startDate,Calendar endDate) {
        LinkedList<GoodsVO> goodsVOs=new LinkedList<GoodsVO>();
        try {
			ArrayList<OrderPO> orderPOs=orderDataService.getAll();
			ArrayList<StoreInPO> storeInPOs=storeFormDataService.getAllStoreInPO();
			ArrayList<StoreOutPO> storeOutPOs=storeFormDataService.getAllStoreOutPO();
			//
			for (OrderPO orderPO : orderPOs) {
				if (this.comp(startDate, endDate, orderPO.getOrderDate())) {
					
				}
			}
			return null;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public List<GoodsVO> filterGoods(String orderNumber) {
        LinkedList<GoodsVO> answer=new LinkedList<GoodsVO>();
        //
        //
        OrderPO orderPO;
		try {
			orderPO = orderDataService.getFormPO(orderNumber);
			StoreInPO storeInPO=storeFormDataService.getStoreInPO(orderNumber);
	        StoreOutPO storeOutPO=storeFormDataService.getStoreOutPO(orderNumber);
	        OrderVO orderVO=(OrderVO)vopoFactory.transPOtoVO(orderPO);
	        StoreInVO storeInVO=(StoreInVO)vopoFactory.transPOtoVO(storeInPO);
	        StoreOutVO storeOutVO=(StoreOutVO)vopoFactory.transPOtoVO(storeOutPO);
	        GoodsVO goodsVO=new GoodsVO(orderVO, storeInVO, storeOutVO);
	        answer.add(goodsVO);
	        return answer;
		} catch (RemoteException e) {
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
