package blService.storeblService;

import blService.FormBLService;
import message.OperationMessage;
import vo.ordervo.OrderVO;
import vo.storevo.StoreOutVO;
import vo.transitvo.TransitVO;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface StoreOutBLService extends FormBLService<StoreOutVO>{

    public String getNewStoreOutID (String date);

    public OperationMessage loadOrder(String orderNumber);

    public OrderVO getOrderVO();

    public TransitVO getTransportVO();

    public OperationMessage clearLocalBuffer();

}
