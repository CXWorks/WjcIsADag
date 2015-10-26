package rmiImpl.storedata;

import message.OperationMessage;
import model.store.StoreModel;
import model.store.StoreModelOperation;
import po.storedata.StoreInPO;
import po.storedata.StoreOutPO;
import rmi.storedata.StoreFormDataService;
import rmi.storedata.StoreModelDataService;
import util.R;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/25.
 */
public class StoreDataImpl extends UnicastRemoteObject implements StoreFormDataService, StoreModelDataService {

    protected StoreDataImpl() throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(1099);
        Naming.rebind(R.string.StoreDataService, this);
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

    public LinkedList<StoreInPO> updateStoreInPOs(String staffID) {
        // TODO Auto-generated method stub
        return new LinkedList<StoreInPO>();
    }

    public LinkedList<StoreInPO> downloadAllStoreInPOs(String centerID) {
        // TODO Auto-generated method stub
        return new LinkedList<StoreInPO>();
    }

    public LinkedList<StoreOutPO> updateStoreOutPOs(String centerID) {
        // TODO Auto-generated method stub
        return new LinkedList<StoreOutPO>();
    }

    public LinkedList<StoreOutPO> downloadAllStoreOutPOs(String staffID) {
        // TODO Auto-generated method stub
        return new LinkedList<StoreOutPO>();
    }

    public OperationMessage uploadModelOperations
            (String centerID, String staffID, List<StoreModelOperation> operations) {
        // TODO Auto-generated method stub
        return new OperationMessage();
    }

    public LinkedList<StoreModelOperation> updateModelOperations(String centerID, String staffID) {
        // TODO Auto-generated method stub
        return new LinkedList<StoreModelOperation>();
    }

    public StoreModel downloadStoreModel(String centerID) {
        // TODO Auto-generated method stub
        return new StoreModel();
    }
}
