package rmiImpl.initaldata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;

import message.OperationMessage;
import po.initialdata.InitialDataPO;
import rmi.initialdata.InitialDataService;

public class InitialDataImpl extends UnicastRemoteObject implements InitialDataService {

	private InitialHelper helper;

	public InitialDataImpl() throws RemoteException {
		super();
		helper = new InitialHelper();
	}

	public Connection getConn() {
		return null;
	}

	public InitialDataPO getInitialDataPO(String version) throws RemoteException {
		// TODO Auto-generated method stub
		return helper.loadFile(version);
	}

	public OperationMessage requestInitData(String staffID) throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage uploadInitialData(String staffID, InitialDataPO newData) throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage abortInitData(String staffID) throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

}
