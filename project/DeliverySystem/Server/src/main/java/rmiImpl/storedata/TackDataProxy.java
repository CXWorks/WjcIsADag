package rmiImpl.storedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;

import message.OperationMessage;
import po.systemdata.SystemState;
import rmi.storedata.TackDataService;
import rmiImpl.initaldata.InitialDataProxy;

/**
 * Created by WJC on 2015/12/24.
 */
public class TackDataProxy extends UnicastRemoteObject implements TackDataService {

	TackDataService tackDataService = new TackDataImpl();

	public TackDataProxy() throws RemoteException {
		super();
	}

	@Override
	public Connection getConn() throws RemoteException {
		return null;
	}

	@Override
	public int getTack(String centerID) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return tackDataService.getTack(centerID);
		return 0;
	}

	@Override
	public OperationMessage setTack(String centerID, String num) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return tackDataService.setTack(centerID, num);
		return null;
	}

}
