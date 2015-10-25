package rmi.storedata;

import po.storedata.StoreInPO;
import po.storedata.StoreOutPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Sissel on 2015/10/23.
 */
public interface StoreFormDataService extends Remote{

    public StoreInPO getStoreInPO (String id) throws RemoteException;

    public StoreOutPO getStoreOutPO(String id) throws RemoteException;

    public List<StoreInPO> updateStoreInPOs(String staffID) throws RemoteException;

    public List<StoreInPO> downloadAllStoreInPOs (String centerID) throws RemoteException;

    public List<StoreOutPO> updateStoreOutPOs (String centerID) throws RemoteException;

    public List<StoreOutPO> downloadAllStoreOutPOs (String staffID) throws RemoteException;

}
