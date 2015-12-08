package rmiImpl.initaldata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;

import message.OperationMessage;
import po.initialdata.InitialDataPO;
import rmi.initialdata.InitialDataService;

public class InitialDataImpl extends UnicastRemoteObject implements InitialDataService {

	private InitialHelper helper;
	private String latest_version;

	public InitialDataImpl() throws RemoteException, ClassNotFoundException {
		super();
		helper = new InitialHelper();
		latest_version = helper.getVersion() - 1 + "";
	}

	public Connection getConn() {
		return null;
	}

	public InitialDataPO getInitialDataPO(String version) throws RemoteException {
		// TODO Auto-generated method stub
		return helper.loadFile(version);
	}

	public OperationMessage requestInitData(String staffID) throws RemoteException, ClassNotFoundException {
		// TODO Auto-generated method stub
		latest_version = helper.saveFile(helper.saveMysql(staffID)).getReason();
		return new OperationMessage();
	}

	public OperationMessage uploadInitialData(String staffID, InitialDataPO newData) throws RemoteException {
		// TODO Auto-generated method stub
		helper.clearMysql();
		helper.loadMysql(newData);
		helper.deleteFile(latest_version);
		try {
			latest_version = helper.saveFile(newData).getReason();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new OperationMessage();
	}

	public OperationMessage abortInitData(String staffID) throws RemoteException {
		// TODO Auto-generated method stub
		helper.loadMysql(helper.loadFile(latest_version));
		return new OperationMessage();
	}

	@Override
	public String getLatest_version(String staffID) throws RemoteException {
		// TODO Auto-generated method stub
		return latest_version;
	}

}
