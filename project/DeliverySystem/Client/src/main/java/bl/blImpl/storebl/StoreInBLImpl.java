package bl.blImpl.storebl;

import bl.blService.storeblService.StoreInBLService;
import bl.clientNetCache.CacheHelper;
import bl.tool.draft.DraftService;
import bl.tool.vopo.VOPOFactory;
import message.CheckFormMessage;
import message.OperationMessage;
import model.store.StoreAreaCode;
import model.store.StoreLocation;
import rmi.storedata.StoreFormDataService;
import util.R;
import vo.ordervo.OrderVO;
import vo.storevo.StoreInVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/26.
 */
public class StoreInBLImpl implements StoreInBLService {
	private StoreFormDataService storeFormDataService;
	private DraftService draftService;
	private VOPOFactory vopoFactory;
	public StoreInBLImpl(DraftService draftService,VOPOFactory vopoFactory){
		this.storeFormDataService=CacheHelper.getStoreFormDataService();
		this.draftService=draftService;
		this.vopoFactory=vopoFactory;
	}
    public StoreLocation getAvailableLocation(StoreAreaCode area) {
        return null;
    }

    public String getNewStoreInID(String date) {
       return null;
    }

    public OperationMessage loadOrder(String orderNumber) {
        return new OperationMessage();
    }

    public OrderVO getOrderVO() {
        return new OrderVO("1123000001");
    }

    public OperationMessage clearLocalBuffer() {
        return new OperationMessage();
    }

    public StoreInVO loadDraft() {
        return null;
    }

    public OperationMessage saveDraft(StoreInVO form) {
        return new OperationMessage();
    }

    public ArrayList<CheckFormMessage> checkFormat(StoreInVO form, boolean isFinal) {
    	ArrayList<CheckFormMessage> result =new ArrayList<CheckFormMessage>();
		CheckFormMessage stub=new CheckFormMessage();
		result.add(stub);
		return result;
    }

    public OperationMessage submit(StoreInVO form) {
        return new OperationMessage();
    }

	/* (non-Javadoc)
	 * @see bl.blService.FormBLService#newID()
	 */
	public String newID() {
		// TODO Auto-generated method stub
		return null;
	}
}
