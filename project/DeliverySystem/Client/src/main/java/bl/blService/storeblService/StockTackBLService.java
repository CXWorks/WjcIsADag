package bl.blService.storeblService;

import message.OperationMessage;
import model.store.StoreAreaCode;
import util.R.string;
import vo.ordervo.OrderVO;
import vo.storevo.StockTackVO;
import vo.storevo.StoreInVO;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface StockTackBLService {

    public StockTackVO getStockTack(String centerID);
    
    public StockTackVO reStockTack(String centerID);

    public OrderVO getOrder(String orderNumber);

    public StoreInVO getStoreInVO(String orderNumber);

    public OperationMessage makeExcel(String path);


}
