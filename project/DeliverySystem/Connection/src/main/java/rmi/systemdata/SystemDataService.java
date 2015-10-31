package rmi.systemdata;

import po.initialdata.InitialDataPO;
import po.systemdata.SystemState;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface SystemDataService {

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "SystemData";
	
    public InitialDataPO getAccountBook(String version);

    public SystemState checkSystemState();

}
