package bl.blImpl.storebl;

import bl.NetReconnect.Reconnect;
import bl.blService.storeblService.StoreOutBLService;
import bl.clientNetCache.CacheHelper;
import message.CheckFormMessage;
import message.OperationMessage;
import rmi.examineService.ExamineSubmitService;
import rmi.orderdata.OrderDataService;
import rmi.storedata.StoreFormDataService;
import rmi.transportdata.CenterOutDataService;
import rmi.transportdata.LoadDataService;
import tool.draft.DraftService;
import tool.vopo.VOPOFactory;
import userinfo.UserInfo;
import util.R;
import vo.ordervo.OrderVO;
import vo.storevo.StoreOutVO;
import vo.transitvo.CenterOutVO;
import vo.transitvo.LoadVO;
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
import po.transportdata.CenterOutPO;
import po.transportdata.LoadPO;

/**
 * Created by Sissel on 2015/10/26.
 */
public class StoreOutBLImpl implements StoreOutBLService {
	private VOPOFactory vopoFactory;
	private DraftService draftService;
	public StoreOutBLImpl(VOPOFactory vopoFactory,DraftService draftService){
		this.draftService=draftService;
		this.vopoFactory=vopoFactory;
		
	}
    public String getNewStoreOutID(String date) {
    	StoreFormDataService storeFormDataService=CacheHelper.getStoreFormDataService();
        try {
			String ID=storeFormDataService.newIDStoreOutPO(UserInfo.getInstitutionID());
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


    public TransitVO getTransportVO(String ID) {
        if(ID.charAt(1)=='7'){
        	CenterOutDataService centerOutDataService=CacheHelper.getTransportDataService();
        	try {
				CenterOutPO centerOutPO=centerOutDataService.getFormPO(ID);
				CenterOutVO centerOutVO=(CenterOutVO)vopoFactory.transPOtoVO(centerOutPO);
				return centerOutVO;
			} catch (RemoteException e) {
				return null;
			}
        }
        else {
			LoadDataService loadDataService=CacheHelper.getLoadDataService();
			try {
				LoadPO loadPO=loadDataService.getFormPO(ID);
				LoadVO loadVO=(LoadVO)vopoFactory.transPOtoVO(loadPO);
				return loadVO;
			} catch (RemoteException e) {
				return null;
			}
		}
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
		return this.getNewStoreOutID(null);

	}
}
