package rmiImpl.transportdata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
import po.transportdata.LoadPO;
import rmi.transportdata.LoadDataService;
import rmiImpl.CommonData;
import rmiImpl.initaldata.InitialDataProxy;

public class LoadDataProxy extends CommonData<LoadPO> implements LoadDataService {

	LoadDataService loadDataService = new LoadDataImpl();

	public LoadDataProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public OperationMessage insert(LoadPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if (!InitialDataProxy.isSystem_on_initial())
			return loadDataService.insert(po);
		return null;
	}

	@Override
	public LoadPO getFormPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		if (!InitialDataProxy.isSystem_on_initial())
			return loadDataService.getFormPO(id);
		return null;
	}

	@Override
	public OperationMessage delete(String id) throws RemoteException {
		// TODO Auto-generated method stub
		if (!InitialDataProxy.isSystem_on_initial())
			return loadDataService.delete(id);
		return null;
	}

	@Override
	public OperationMessage update(LoadPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if (!InitialDataProxy.isSystem_on_initial())
			return loadDataService.update(po);
		return null;
	}

	@Override
	public String newID(String unitID) throws RemoteException {
		// TODO Auto-generated method stub
		if (!InitialDataProxy.isSystem_on_initial())
			return loadDataService.newID(unitID);
		return null;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
		// TODO Auto-generated method stub
		if (!InitialDataProxy.isSystem_on_initial())
			return loadDataService.clear();
		return null;
	}

	@Override
	public ArrayList<LoadPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		if (!InitialDataProxy.isSystem_on_initial())
			return loadDataService.getAll();
		return null;
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
