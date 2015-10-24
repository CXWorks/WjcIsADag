package rmi.storedata;

import message.OperationMessage;
import model.store.StoreModel;
import model.store.StoreModelOperation;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by Sissel on 2015/10/23.
 */
public interface StoreModelDataService extends Remote {
    public OperationMessage uploadModelOperations
            (String centerID, String staffID, List<StoreModelOperation> operations);

    public List<StoreModelOperation> updateModelOperations
            (String centerID, String staffID);

    public StoreModel downloadStoreModel (String centerID);

}
