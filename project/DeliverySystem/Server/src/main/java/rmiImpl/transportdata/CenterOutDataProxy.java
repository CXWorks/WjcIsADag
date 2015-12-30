package rmiImpl.transportdata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import message.OperationMessage;
import po.systemdata.SystemState;
import po.transportdata.CenterOutPO;
import rmi.transportdata.CenterOutDataService;
import rmiImpl.CommonData;
import rmiImpl.initaldata.InitialDataProxy;

public class CenterOutDataProxy extends CommonData<CenterOutPO> implements CenterOutDataService {

	CenterOutDataService centerOutDataService = new CenterOutDataImpl();

	public CenterOutDataProxy() throws RemoteException {
		super();
	}

	@Override
	public OperationMessage insert(CenterOutPO po) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return centerOutDataService.insert(po);
		return null;
	}

	@Override
	public CenterOutPO getFormPO(String id) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return centerOutDataService.getFormPO(id);
		return null;
	}

	@Override
	public OperationMessage delete(String id) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return centerOutDataService.delete(id);
		return null;
	}

	@Override
	public OperationMessage update(CenterOutPO po) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return centerOutDataService.update(po);
		return null;
	}

	@Override
	public String newID(String unitID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return centerOutDataService.newID(unitID);
		return null;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return centerOutDataService.clear();
		return null;
	}

	@Override
	public ArrayList<CenterOutPO> getAll() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return centerOutDataService.getAll();
		return null;
	}

	@Override
	public Connection getConn() throws RemoteException {
		return null;
	}

	@Override
	public String newTransID(String unitID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return centerOutDataService.newTransID(unitID);
		return null;
	}

	@Override
	public List<CenterOutPO> getHistory(String creatorID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return centerOutDataService.getHistory(creatorID);
		return null;
	}

}
