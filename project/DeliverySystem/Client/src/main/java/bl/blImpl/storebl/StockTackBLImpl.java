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

    public StockTackVO filtLocation(StoreAreaCode area, int row, int shelf, int position) {
        return new StockTackVO();
    }

    public OrderVO getOrder(String orderNumber) {
        return new OrderVO();
    }

    public StoreInVO getStoreInVO(String orderNumber) {
        return new StoreInVO();
    }

    public OperationMessage makeExcel(String path) {
        return new OperationMessage();
    }
}
