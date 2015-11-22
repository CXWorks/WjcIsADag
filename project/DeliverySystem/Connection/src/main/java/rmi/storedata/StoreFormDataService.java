package rmi.storedata;

import po.CommonPO;
import po.storedata.StoreInPO;
import po.storedata.StoreOutPO;
import rmi.DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/23.
 */
public interface StoreFormDataService extends DataService<CommonPO>{
	
    public StoreInPO getStoreInPO (String id) throws RemoteException;

    public StoreOutPO getStoreOutPO(String id) throws RemoteException;

    public LinkedList<StoreInPO> updateStoreInPOs(String staffID) throws RemoteException;

    public LinkedList<StoreInPO> downloadAllStoreInPOs (String centerID) throws RemoteException;

    public LinkedList<StoreOutPO> updateStoreOutPOs (String centerID) throws RemoteException;

    public LinkedList<StoreOutPO> downloadAllStoreOutPOs (String staffID) throws RemoteException;

}
