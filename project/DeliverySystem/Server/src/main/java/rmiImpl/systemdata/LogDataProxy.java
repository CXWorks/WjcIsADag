package rmiImpl.systemdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;

import message.OperationMessage;
import po.systemdata.LogPO;
import po.systemdata.SystemState;
import rmi.systemdata.LogDataService;
import rmiImpl.initaldata.InitialDataProxy;

public class LogDataProxy extends UnicastRemoteObject implements LogDataService {

	LogDataService logDataService = new LogDataImpl();

	public LogDataProxy() throws RemoteException {
		super();
	}

	@Override
	public OperationMessage insert(LogPO po) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return logDataService.insert(po);
		return null;
	}

	@Override
	public ArrayList<LogPO> getByTime(Calendar start, Calendar end) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return logDataService.getByTime(start, end);
		return null;
	}

	@Override
	public ArrayList<LogPO> getAll() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return logDataService.getAll();
		return null;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return logDataService.clear();
		return null;
	}

}
