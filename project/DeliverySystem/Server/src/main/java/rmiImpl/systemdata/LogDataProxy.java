package rmiImpl.systemdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;

import message.OperationMessage;
import po.systemdata.LogPO;
import rmi.systemdata.LogDataService;
import rmiImpl.initaldata.InitialDataProxy;

public class LogDataProxy extends UnicastRemoteObject implements LogDataService {

	LogDataService logDataService = new LogDataImpl();

	public LogDataProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationMessage insert(LogPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return logDataService.insert(po);
		return null;
	}

	@Override
	public ArrayList<LogPO> getByTime(Calendar start, Calendar end) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return logDataService.getByTime(start, end);
		return null;
	}

}
