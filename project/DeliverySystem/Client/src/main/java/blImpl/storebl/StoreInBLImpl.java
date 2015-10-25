package blImpl.storebl;

import blService.storeblService.StoreInBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import model.store.StoreAreaCode;
import model.store.StoreLocation;
import vo.ordervo.OrderVO;
import vo.storevo.StoreInVO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/26.
 */
public class StoreInBLImpl implements StoreInBLService {
    public StoreLocation getAvailableLocation(StoreAreaCode area) {
        return new StoreLocation();
    }

    public String getNewStoreInID(String date) {
        return "222333";
    }

    public OperationMessage loadOrder(String orderNumber) {
        return new OperationMessage();
    }

    public OrderVO getOrderVO() {
        return new OrderVO();
    }

    public OperationMessage clearLocalBuffer() {
        return new OperationMessage();
    }

    public StoreInVO loadDraft() {
        return new StoreInVO();
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
}
