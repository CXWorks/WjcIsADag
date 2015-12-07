package rmiImpl.initaldata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;

import message.OperationMessage;
import po.initialdata.InitialDataPO;
import rmi.initialdata.InitialDataService;

public class InitialDataProxy extends UnicastRemoteObject implements InitialDataService{

	private InitialDataService InitialDataService = new InitialDataImpl();
    private static boolean System_on_initial = false;

	protected InitialDataProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean isSystem_on_initial() {
		return System_on_initial;
	}

	public static void setSystem_on_initial(boolean system_on_initial) {
		System_on_initial = system_on_initial;
	}

	@Override
	public InitialDataPO getInitialDataPO(String version) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationMessage requestInitData(String staffID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationMessage uploadInitialData(String staffID, InitialDataPO newData) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationMessage abortInitData(String staffID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
