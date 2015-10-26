package rmi.systemdata;

import po.initialdata.InitialDataPO;
import po.systemdata.SystemState;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface SystemDataService {

    public InitialDataPO getAccountBook(String version);

    public SystemState checkSystemState();

}
