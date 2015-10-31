package rmi.storedata;

import po.storedata.StoreInPO;
import po.storedata.StoreOutPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/23.
 */
public interface StoreFormDataService extends Remote{

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "StoreFormData";
	
    public StoreInPO getStoreInPO (String id) throws RemoteException;

    public StoreOutPO getStoreOutPO(String id) throws RemoteException;

    public LinkedList<StoreInPO> updateStoreInPOs(String staffID) throws RemoteException;

    public LinkedList<StoreInPO> downloadAllStoreInPOs (String centerID) throws RemoteException;

    public LinkedList<StoreOutPO> updateStoreOutPOs (String centerID) throws RemoteException;

    public LinkedList<StoreOutPO> downloadAllStoreOutPOs (String staffID) throws RemoteException;

}
