package bl.blImpl.storebl;

import bl.NetReconnect.Reconnect;
import bl.blService.storeblService.StoreInBLService;
import bl.clientNetCache.CacheHelper;
import tool.draft.DraftService;
import tool.vopo.VOPOFactory;
import userinfo.UserInfo;
import message.CheckFormMessage;
import message.OperationMessage;
import model.store.StoreArea;
import model.store.StoreAreaCode;
import model.store.StoreLocation;
import rmi.examineService.ExamineSubmitService;
import rmi.orderdata.OrderDataService;
import rmi.storedata.StoreFormDataService;
import rmi.storedata.StoreModelDataService;
import vo.ordervo.OrderVO;
import vo.storevo.StoreInVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.FormEnum;
import po.orderdata.OrderPO;
import po.storedata.StoreInPO;

/**
 * Created by Sissel on 2015/10/26.
 */
public class StoreInBLImpl implements StoreInBLService {
	private DraftService draftService;
	private VOPOFactory vopoFactory;
	public StoreInBLImpl(VOPOFactory vopoFactory, DraftService draftService){
		
		this.draftService=draftService;
		this.vopoFactory=vopoFactory;
	}
    public StoreLocation getAvailableLocation(StoreAreaCode area) {
        StoreModelDataService storeModelDataService=CacheHelper.getStoreModelDataService();
        try {
			StoreArea storeArea=storeModelDataService.getArea(UserInfo.getInstitutionID(),area);
			ArrayList<StoreLocation> storeLocation=storeArea.getList();
			StoreLocation ans = null;
			for (StoreLocation location : storeLocation) {
				if(location.getOrderID() == null || location.getOrderID().equals("")){
					ans = location;
					break;
				}
			}
			return ans;
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return null;
		}
    }

    public String getNewStoreInID(String date) {
    	StoreFormDataService storeFormDataService=CacheHelper.getStoreFormDataService();
       try {
		String ID=storeFormDataService.newIDStoreInPO(UserInfo.getInstitutionID());
		return ID;
	} catch (RemoteException e) {
		Reconnect.ReConnectFactory();
		return null;
	}
    }

    public OrderVO loadOrder(String orderNumber) {
        OrderDataService orderDataService=CacheHelper.getOrderDataService();
        try {
			OrderPO po=orderDataService.getFormPO(orderNumber);
			OrderVO vo=(OrderVO)vopoFactory.transPOtoVO(po);
			return vo;
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return null;
		}

    }

    public StoreInVO loadDraft() {
        StoreInVO vo=(StoreInVO)draftService.getDraft(FormEnum.STORE_IN);
        return vo;
    }

    public OperationMessage saveDraft(StoreInVO form) {
        return draftService.saveDraft(form);
    }

    public ArrayList<CheckFormMessage> checkFormat(StoreInVO form, boolean isFinal) {
    	ArrayList<CheckFormMessage> result =new ArrayList<CheckFormMessage>();
		CheckFormMessage stub=new CheckFormMessage();
		result.add(stub);
		return result;
    }

    public OperationMessage submit(StoreInVO form) {
        ExamineSubmitService examineSubmitService=CacheHelper.getExamineSubmitService();
    	StoreFormDataService storeFormDataService=CacheHelper.getStoreFormDataService();
        StoreInPO po=(StoreInPO)vopoFactory.transVOtoPO(form);
        try {
			return examineSubmitService.submit(po);
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return new OperationMessage(false, "net error");
		}
    }

	/* (non-Javadoc)
	 * @see bl.blService.FormBLService#newID()
	 */
	public String newID() {
		// TODO Auto-generated method stub
		return this.getNewStoreInID(null);
	}
}
