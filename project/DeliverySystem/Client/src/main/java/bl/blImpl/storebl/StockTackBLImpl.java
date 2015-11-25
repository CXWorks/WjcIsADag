package bl.blImpl.storebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.orderdata.OrderPO;
import po.storedata.StoreInPO;
import rmi.orderdata.OrderDataService;
import rmi.storedata.StoreFormDataService;
import rmi.storedata.StoreModelDataService;
import tool.excel.Excel;
import bl.blService.storeblService.StockTackBLService;
import bl.clientNetCache.CacheHelper;
import message.OperationMessage;
import model.store.StoreArea;
import model.store.StoreAreaCode;
import vo.ordervo.OrderVO;
import vo.storevo.StockTackVO;
import vo.storevo.StoreInVO;

/**
 * Created by Sissel on 2015/10/26.
 */
public class StockTackBLImpl implements StockTackBLService {
	private StoreModelDataService storeModelDataService;
	private StoreFormDataService storeFormDataService;
	//
	public StockTackBLImpl(){
		this.storeFormDataService=CacheHelper.getStoreFormDataService();
		this.storeModelDataService=CacheHelper.getStoreModelDataService();
	}
    public StockTackVO getStockTack() {
        return new StockTackVO();
    }

    public OrderVO getOrder(String orderNumber) {
		OrderDataService orderDataService = CacheHelper.getOrderDataService();
		try {
			OrderPO po = orderDataService.getFormPO(orderNumber);
			OrderVO vo = new OrderVO(po);
			return vo;
		} catch (RemoteException e) {
			return null;
		}
	}

    public StoreInVO getStoreInVO(String orderNumber) {
        try {
			StoreInPO storeInPO=storeFormDataService.getStoreInPO(orderNumber);
			StoreInVO storeInVO=new StoreInVO(storeInPO);
			return storeInVO;
		} catch (RemoteException e) {
			return null;
		}
    }

    public OperationMessage makeExcel(String path) {
        try {
        	ArrayList<StoreArea> storeArea=new ArrayList<StoreArea>(4);
			StoreArea storeArea1=storeModelDataService.getArea(StoreAreaCode.AIR);
			storeArea.add(storeArea1);
			StoreArea storeArea2=storeModelDataService.getArea(StoreAreaCode.AIR);
			storeArea.add(storeArea2);
			StoreArea storeArea3=storeModelDataService.getArea(StoreAreaCode.AIR);
			storeArea.add(storeArea3);
			StoreArea storeArea4=storeModelDataService.getArea(StoreAreaCode.AIR);
			storeArea.add(storeArea4);
			return Excel.exportToExcel(path, storeArea);
		} catch (RemoteException e) {
			return new OperationMessage(false,"net error");
		}
    }
}
