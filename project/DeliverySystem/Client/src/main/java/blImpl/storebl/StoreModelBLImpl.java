package blImpl.storebl;

import blService.storeblService.StoreModelBLService;
import message.OperationMessage;
import model.store.StoreAreaCode;
import model.store.StoreModel;
import rmi.storedata.StoreModelDataService;
import util.R;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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

    public static void drive(StoreModelDataService smds) throws RemoteException {
        if(smds.downloadStoreModel("222333") != null)
            System.out.println("downloadStoreModel tested");
        if(smds.updateModelOperations("222333", "222333") != null)
            System.out.println("updateModelOperations tested");
        if(smds.uploadModelOperations("222", "333", null) != null)
            System.out.println("uploadModelOperations tested");
    }

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        StoreModelDataService smds = (StoreModelDataService) Naming.lookup
                ("rmi://" + R.string.LocalHost + "/" + R.string.StoreDataService);
        drive(smds);
    }
}
