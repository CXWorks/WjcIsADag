package bl.blService.storeblService;

import message.OperationMessage;
import model.store.StoreAreaCode;

import java.util.Observer;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface StoreModelBLService {

    public OperationMessage setWarningLine(double percent);

    public OperationMessage reducePartition(StoreAreaCode area, int number);

    public OperationMessage expandPartition(StoreAreaCode area, int number);

    public OperationMessage deleteRow(StoreAreaCode area, int rowNum, boolean confirmed);

    public OperationMessage addRow(StoreAreaCode area, int initCapacity);

    public OperationMessage adjustRow(StoreAreaCode area, int rowNum, int newCapacity, boolean confirmed);
    

}
