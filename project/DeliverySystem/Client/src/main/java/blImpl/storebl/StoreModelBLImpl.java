package blImpl.storebl;

import blService.storeblService.StoreModelBLService;
import message.OperationMessage;
import model.store.StoreAreaCode;

import java.util.Observer;

/**
 * Created by Sissel on 2015/10/26.
 */
public class StoreModelBLImpl implements StoreModelBLService {
    public OperationMessage setWarningLine(double percent) {
        return new OperationMessage();
    }

    public void addObserver(Observer o) {

    }

    public OperationMessage reducePartition(StoreAreaCode area, int number) {
        return new OperationMessage();
    }

    public OperationMessage expandPartition(StoreAreaCode area, int number) {
        return new OperationMessage();
    }

    public OperationMessage deleteRow(StoreAreaCode area, int rowNum, boolean confirmed) {
        return new OperationMessage();
    }

    public OperationMessage addRow(StoreAreaCode area, int initCapacity) {
        return new OperationMessage();
    }

    public OperationMessage adjustRow(StoreAreaCode area, int rowNum, int newCapacity, boolean confirmed) {
        return new OperationMessage();
    }

    public OperationMessage clearLocalBuffer() {
        return new OperationMessage();
    }
}
