package rmiImpl.receivedata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import message.OperationMessage;
import po.receivedata.ReceivePO;
import po.systemdata.SystemState;
import rmi.receivedata.ReceiveDataService;
import rmiImpl.CommonData;
import rmiImpl.initaldata.InitialDataProxy;

public class ReceiveDataProxy extends CommonData<ReceivePO> implements ReceiveDataService {

	ReceiveDataService receiveDataService = new ReceiveDataImpl();

	public ReceiveDataProxy() throws RemoteException {
		super();
	}

	@Override
	public OperationMessage insert(ReceivePO po) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return receiveDataService.insert(po);
		return null;
	}

	@Override
	public ReceivePO getFormPO(String id) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return receiveDataService.getFormPO(id);
		return null;
	}

	@Override
	public OperationMessage delete(String id) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return receiveDataService.delete(id);
		return null;
	}

	@Override
	public OperationMessage update(ReceivePO po) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return receiveDataService.update(po);
		return null;
	}

	@Override
	public String newID(String unitID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return receiveDataService.newID(unitID);
		return null;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return receiveDataService.clear();
		return null;
	}

	@Override
	public ArrayList<ReceivePO> getAll() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return receiveDataService.getAll();
		return null;
	}

	@Override
	public List<ReceivePO> getHistory(String creatorID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return receiveDataService.getHistory(creatorID);
		return null;
	}
}
