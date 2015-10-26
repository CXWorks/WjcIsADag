package rmiImpl.initaldata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import message.OperationMessage;
import po.initialdata.InitialDataPO;
import rmi.initialdata.InitialDataService;

public class InitialDataImpl extends UnicastRemoteObject implements InitialDataService{

	public InitialDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public InitialDataPO getInitialDataPO(String version)
			throws RemoteException {
		// TODO Auto-generated method stub
		return new InitialDataPO();
	}

	public OperationMessage requestInitData(String staffID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage uploadInitialData(String staffID,
			InitialDataPO newData) throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage abortInitData(String staffID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

}
