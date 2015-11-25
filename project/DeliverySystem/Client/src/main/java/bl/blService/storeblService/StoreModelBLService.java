package bl.blService.storeblService;

import message.OperationMessage;
import model.store.StoreAreaCode;

import java.util.Observer;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface StoreModelBLService {

    public OperationMessage setWarningLine(double percent);

    public OperationMessage reducePartition(StoreAreaCode area, int shelfNumber);

    public OperationMessage expandPartition(StoreAreaCode area, int shelfNumber);

    public OperationMessage moveShelf(StoreAreaCode code_now, int row_now,
			int shelf_now, StoreAreaCode code, int row, int shelf);
    

}
