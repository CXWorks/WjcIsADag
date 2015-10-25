package blService.storeblService;

import blService.FormBLService;
import message.OperationMessage;
import vo.storevo.StoreOutVO;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface StoreOutBLService extends FormBLService<StoreOutVO>{

    public String getNewStoreOutID (String date);

    public OperationMessage loadOrder(String orderNumber);

    //public OrderVO getOrderVO(); TODO

    //public TransitVO getTransportVO(); TODO

    public OperationMessage clearLocalBuffer();

}
