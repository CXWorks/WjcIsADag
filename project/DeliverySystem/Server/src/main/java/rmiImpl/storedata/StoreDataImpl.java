package rmiImpl.storedata;

import message.OperationMessage;
import model.store.StoreModel;
import model.store.StoreModelOperation;
import po.storedata.StoreInPO;
import po.storedata.StoreOutPO;
import rmi.storedata.StoreFormDataService;
import rmi.storedata.StoreModelDataService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/25.
 */
public class StoreDataImpl extends UnicastRemoteObject implements StoreFormDataService, StoreModelDataService {

    protected StoreDataImpl() throws RemoteException, MalformedURLException {
        Naming.rebind("StoreDataService", this);
    }

    public static void main(String[] args) throws MalformedURLException, RemoteException {
        new StoreDataImpl();
    }

    public StoreInPO getStoreInPO(String id) {
        // TODO Auto-generated method stub
        return new StoreInPO();
    }

    public StoreOutPO getStoreOutPO(String id) {
        // TODO Auto-generated method stub
        return new StoreOutPO();
    }

    public List<StoreInPO> updateStoreInPOs(String staffID) {
        // TODO Auto-generated method stub
        return new LinkedList<StoreInPO>();
    }

    public List<StoreInPO> downloadAllStoreInPOs(String centerID) {
        // TODO Auto-generated method stub
        return new LinkedList<StoreInPO>();
    }

    public List<StoreOutPO> updateStoreOutPOs(String centerID) {
        // TODO Auto-generated method stub
        return new LinkedList<StoreOutPO>();
    }

    public List<StoreOutPO> downloadAllStoreOutPOs(String staffID) {
        // TODO Auto-generated method stub
        return new LinkedList<StoreOutPO>();
    }

    public OperationMessage uploadModelOperations
            (String centerID, String staffID, List<StoreModelOperation> operations) {
        // TODO Auto-generated method stub
        return new OperationMessage();
    }

    public List<StoreModelOperation> updateModelOperations(String centerID, String staffID) {
        // TODO Auto-generated method stub
        return new LinkedList<StoreModelOperation>();
    }

    public StoreModel downloadStoreModel(String centerID) {
        // TODO Auto-generated method stub
        return new StoreModel();
    }
}
