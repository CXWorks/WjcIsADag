package rmi.systemdata;

import po.systemdata.AccountBookPO;
import po.systemdata.SystemState;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface SystemDataService {

    public AccountBookPO getAccountBook(String version);

    public SystemState checkSystemState();

}
