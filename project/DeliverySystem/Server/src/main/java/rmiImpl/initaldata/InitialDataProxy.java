package rmiImpl.initaldata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;

import message.OperationMessage;
import po.initialdata.InitialDataPO;
import po.systemdata.SystemState;
import rmi.initialdata.InitialDataService;
import rmi.systemdata.SystemDataService;

public class InitialDataProxy extends UnicastRemoteObject implements InitialDataService,SystemDataService {

	private InitialDataService InitialDataService = new InitialDataImpl();
	private static SystemState state = SystemState.NORMAL;

	private static String ID = "02000001";

	public InitialDataProxy() throws RemoteException,ClassNotFoundException {
		super();
	}

	public static String getID() {
		return ID;
	}

	public static SystemState getState() {
		return state;
	}

	public static void setState(SystemState state) {
		InitialDataProxy.state = state;
	}

	@Override
	public Connection getConn() throws RemoteException {
		return null;
	}

	@Override
	public InitialDataPO getInitialDataPO(String version) throws RemoteException {
		return InitialDataService.getInitialDataPO(version);
	}

	@Override
	public OperationMessage requestInitData(String staffID) throws RemoteException, ClassNotFoundException {
		ID = staffID;
		this.setState(SystemState.INITIALIZING);
		return InitialDataService.requestInitData(staffID);
	}

	@Override
	public OperationMessage uploadInitialData(String staffID, InitialDataPO newData) throws RemoteException {
		if(ID.equalsIgnoreCase(staffID))
			return InitialDataService.uploadInitialData(staffID, newData);
		return new OperationMessage(false,"ID不正确");
	}

	@Override
	public OperationMessage abortInitData(String staffID) throws RemoteException {
		this.setState(SystemState.NORMAL);
		if(ID.equalsIgnoreCase(staffID))
			return InitialDataService.abortInitData(staffID);
		return new OperationMessage(false,"ID不正确");
	}

	@Override
	public String getLatest_version(String staffID) throws RemoteException {
		if(ID.equalsIgnoreCase(staffID))
			return InitialDataService.getLatest_version(staffID);
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.systemdata.SystemDataService#checkSystemState()
	 */
	@Override
	public SystemState checkSystemState() throws RemoteException {
		return this.state;
	}

}
