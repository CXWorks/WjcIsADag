package rmiImpl.initaldata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;

import message.OperationMessage;
import po.initialdata.InitialDataPO;
import po.systemdata.SystemState;
import rmi.initialdata.InitialDataService;

public class InitialDataProxy extends UnicastRemoteObject implements InitialDataService {

	private InitialDataService InitialDataService = new InitialDataImpl();
	private static SystemState state = SystemState.NORMAL;

	private static String ID = "";

	protected InitialDataProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static SystemState getState() {
		return state;
	}

	public static void setState(SystemState state) {
		InitialDataProxy.state = state;
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InitialDataPO getInitialDataPO(String version) throws RemoteException {
		// TODO Auto-generated method stub
		return InitialDataService.getInitialDataPO(version);
	}

	@Override
	public OperationMessage requestInitData(String staffID) throws RemoteException {
		// TODO Auto-generated method stub
		ID = staffID;
		this.setState(SystemState.INITIALIZING);
		return InitialDataService.requestInitData(staffID);
	}

	@Override
	public OperationMessage uploadInitialData(String staffID, InitialDataPO newData) throws RemoteException {
		// TODO Auto-generated method stub
		if(ID.equalsIgnoreCase(staffID))
			return InitialDataService.uploadInitialData(staffID, newData);
		return new OperationMessage(false,"ID不正确");
	}

	@Override
	public OperationMessage abortInitData(String staffID) throws RemoteException {
		// TODO Auto-generated method stub
		if(ID.equalsIgnoreCase(staffID))
			return InitialDataService.abortInitData(staffID);
		return new OperationMessage(false,"ID不正确");
	}

}
