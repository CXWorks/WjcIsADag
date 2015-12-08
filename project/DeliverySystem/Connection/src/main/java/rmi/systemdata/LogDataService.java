package rmi.systemdata;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

import message.OperationMessage;
import po.systemdata.LogPO;
import rmi.DataService;

/**
 * Created by WJC on 2015/11/29.
 */
public interface LogDataService extends DataService<LogPO>{

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "LogData";

	public OperationMessage insert(LogPO po) throws RemoteException;

	public OperationMessage clear() throws RemoteException;

	public ArrayList<LogPO> getByTime(Calendar start,Calendar end) throws RemoteException;
}
