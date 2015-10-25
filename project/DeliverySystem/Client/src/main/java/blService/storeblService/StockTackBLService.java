package blService.storeblService;

import message.OperationMessage;
import model.store.StoreAreaCode;
import vo.ordervo.OrderVO;
import vo.storevo.StockTackVO;
import vo.storevo.StoreInVO;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface StockTackBLService {

    public StockTackVO getStockTack();

    public StockTackVO filtLocation(StoreAreaCode area, int row, int shelf, int position);

    public OrderVO getOrder(String orderNumber);

    public StoreInVO getStoreInVO(String orderNumber);

    public OperationMessage makeExcel(String path);


}
