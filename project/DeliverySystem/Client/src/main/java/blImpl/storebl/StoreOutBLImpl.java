package blImpl.storebl;

import blService.storeblService.StoreOutBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import vo.ordervo.OrderVO;
import vo.storevo.StoreOutVO;
import vo.transitvo.CenterOutVO;
import vo.transitvo.TransitVO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/26.
 */
public class StoreOutBLImpl implements StoreOutBLService {
    public String getNewStoreOutID(String date) {
        return "222333";
    }

    public OperationMessage loadOrder(String orderNumber) {
        return new OperationMessage();
    }

    public OrderVO getOrderVO() {
        return new OrderVO();
    }

    public TransitVO getTransportVO() {
        return new CenterOutVO();
    }

    public OperationMessage clearLocalBuffer() {
        return new OperationMessage();
    }

    public StoreOutVO loadDraft() {
        return new StoreOutVO();
    }

    public OperationMessage saveDraft(StoreOutVO form) {
        return new OperationMessage();
    }

    public List<CheckFormMessage> checkFormat(StoreOutVO form, boolean isFinal) {
        return new LinkedList<CheckFormMessage>();
    }

    public OperationMessage submit(StoreOutVO form) {
        return new OperationMessage();
    }
}
