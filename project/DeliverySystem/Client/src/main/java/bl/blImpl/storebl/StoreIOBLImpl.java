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
	private VOPOFactory vopoFactory;
    public List<GoodsVO> getGoodsInfo(String inDate, String inTime, String outDate, String outTime) {
        return new LinkedList<GoodsVO>();
    }

    public List<GoodsVO> filterGoods(String orderNumber) {
        LinkedList<GoodsVO> answer=new LinkedList<GoodsVO>();
        //
        OrderDataService orderDataService=CacheHelper.getOrderDataService();
        StoreFormDataService storeFormDataService=CacheHelper.getStoreFormDataService();
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
}
