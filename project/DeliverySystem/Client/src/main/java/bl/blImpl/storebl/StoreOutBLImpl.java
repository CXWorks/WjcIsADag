package bl.blImpl.storebl;

import bl.blService.storeblService.StoreOutBLService;
import bl.clientNetCache.CacheHelper;
import message.CheckFormMessage;
import message.OperationMessage;
import rmi.examineService.ExamineSubmitService;
import rmi.orderdata.OrderDataService;
import rmi.storedata.StoreFormDataService;
import tool.draft.DraftService;
import tool.vopo.VOPOFactory;
import userinfo.UserInfo;
import util.R;
import vo.ordervo.OrderVO;
import vo.storevo.StoreOutVO;
import vo.transitvo.CenterOutVO;
import vo.transitvo.TransitVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import po.FormEnum;
import po.orderdata.OrderPO;
import po.storedata.StoreOutPO;

/**
 * Created by Sissel on 2015/10/26.
 */
public class StoreOutBLImpl implements StoreOutBLService {
	private StoreFormDataService storeFormDataService;
	private VOPOFactory vopoFactory;
	private DraftService draftService;
	public StoreOutBLImpl(VOPOFactory vopoFactory,DraftService draftService){
		this.draftService=draftService;
		this.vopoFactory=vopoFactory;
		this.storeFormDataService=CacheHelper.getStoreFormDataService();
	}
    public String getNewStoreOutID(String date) {
        try {
			String ID=storeFormDataService.newIDStoreOutPO(UserInfo.getInstitutionID());
			return ID;
		} catch (RemoteException e) {
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
			return null;
		}
    }


    public TransitVO getTransportVO(String ID) {
        return new CenterOutVO("050010001201511230000002");
    }

    public StoreOutVO loadDraft() {
        StoreOutVO vo=(StoreOutVO)draftService.getDraft(FormEnum.STORE_OUT);
        return vo;
    }

    public OperationMessage saveDraft(StoreOutVO form) {
        return draftService.saveDraft(form);
    }

    public ArrayList<CheckFormMessage> checkFormat(StoreOutVO form, boolean isFinal) {
    	ArrayList<CheckFormMessage> result =new ArrayList<CheckFormMessage>();
		CheckFormMessage stub=new CheckFormMessage();
		result.add(stub);
		return result;
    }

    public OperationMessage submit(StoreOutVO form) {
        StoreOutPO po=(StoreOutPO)vopoFactory.transVOtoPO(form);
        ExamineSubmitService examineSubmitService=CacheHelper.getExamineSubmitService();
        try {
			return examineSubmitService.submit(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
    }


	/* (non-Javadoc)
	 * @see bl.blService.FormBLService#newID()
	 */
	public String newID() {
		// TODO Auto-generated method stub
		return null;
	}
}
