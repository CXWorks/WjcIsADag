package rmiImpl.transportdata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
import po.systemdata.SystemState;
import po.transportdata.LoadPO;
import rmi.transportdata.LoadDataService;
import rmiImpl.CommonData;
import rmiImpl.initaldata.InitialDataProxy;

public class LoadDataProxy extends CommonData<LoadPO> implements LoadDataService {

	LoadDataService loadDataService = new LoadDataImpl();

	public LoadDataProxy() throws RemoteException {
		super();
	}

	@Override
	public OperationMessage insert(LoadPO po) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return loadDataService.insert(po);
		return null;
	}

	@Override
	public LoadPO getFormPO(String id) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return loadDataService.getFormPO(id);
		return null;
	}

	@Override
	public OperationMessage delete(String id) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return loadDataService.delete(id);
		return null;
	}

	@Override
	public OperationMessage update(LoadPO po) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return loadDataService.update(po);
		return null;
	}

	@Override
	public String newID(String unitID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return loadDataService.newID(unitID);
		return null;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return loadDataService.clear();
		return null;
	}

	@Override
	public ArrayList<LoadPO> getAll() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return loadDataService.getAll();
		return null;
	}

	@Override
	public Connection getConn() throws RemoteException {
		return null;
	}

	@Override
	public String newTransID(String unitID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return loadDataService.newTransID(unitID);
		return null;
	}

}
