package rmi.systemdata;

import java.rmi.RemoteException;
import java.sql.Connection;

import po.CommonPO;
import po.initialdata.InitialDataPO;
import po.systemdata.SystemState;
import rmi.DataService;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface SystemDataService extends DataService<InitialDataPO>{

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "SystemData";

    public SystemState checkSystemState() throws RemoteException;

}
