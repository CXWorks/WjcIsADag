package rmi.storedata;

import message.OperationMessage;
import model.store.StoreModel;
import model.store.StoreModelOperation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/23.
 */
public interface StoreModelDataService extends Remote {
	
	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "StoreModelData";
	
    public OperationMessage uploadModelOperations
            (String centerID, String staffID, List<StoreModelOperation> operations) throws RemoteException;

    public LinkedList<StoreModelOperation> updateModelOperations
            (String centerID, String staffID) throws RemoteException;

    public StoreModel downloadStoreModel (String centerID) throws RemoteException;

}
