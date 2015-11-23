package bl.blImpl.storebl;

import bl.blService.storeblService.StockTackBLService;
import message.OperationMessage;
import model.store.StoreAreaCode;
import vo.ordervo.OrderVO;
import vo.storevo.StockTackVO;
import vo.storevo.StoreInVO;

/**
 * Created by Sissel on 2015/10/26.
 */
public class StockTackBLImpl implements StockTackBLService {
    public StockTackVO getStockTack() {
        return new StockTackVO();
    }

    public OrderVO getOrder(String orderNumber) {
        return new OrderVO("1123000001");
    }

    public StoreInVO getStoreInVO(String orderNumber) {
        return new StoreInVO("050010001201511230000002");
    }

    public OperationMessage makeExcel(String path) {
        return new OperationMessage();
    }
}
