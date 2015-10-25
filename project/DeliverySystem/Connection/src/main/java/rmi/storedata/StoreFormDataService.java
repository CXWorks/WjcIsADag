package rmi.storedata;

import po.storedata.StoreInPO;
import po.storedata.StoreOutPO;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by Sissel on 2015/10/23.
 */
public interface StoreFormDataService extends Remote{

    public StoreInPO getStoreInPO (String id);

    public StoreOutPO getStoreOutPO(String id);

    public List<StoreInPO> updateStoreInPOs(String staffID);

    public List<StoreInPO> downloadAllStoreInPOs (String centerID);

    public List<StoreOutPO> updateStoreOutPOs (String centerID);

    public List<StoreOutPO> downloadAllStoreOutPOs (String staffID);

}
