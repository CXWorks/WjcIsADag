package rmiImpl.deliverdata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import message.OperationMessage;
import po.deliverdata.DeliverPO;
import po.systemdata.SystemState;
import rmi.deliverdata.DeliverDataService;
import rmiImpl.CommonData;
import rmiImpl.initaldata.InitialDataProxy;

public class DeliverDataProxy extends CommonData<DeliverPO> implements DeliverDataService {

	DeliverDataService deliverDataService = new DeliverDataImpl();

	public DeliverDataProxy() throws RemoteException {
		super();
	}

	@Override
	public OperationMessage insert(DeliverPO po) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return deliverDataService.insert(po);
		return null;
	}

	@Override
	public DeliverPO getFormPO(String id) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return deliverDataService.getFormPO(id);
		return null;
	}

	@Override
	public OperationMessage delete(String id) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return deliverDataService.delete(id);
		return null;
	}

	@Override
	public OperationMessage update(DeliverPO po) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return deliverDataService.update(po);
		return null;
	}

	@Override
	public String newID(String unitID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return deliverDataService.newID(unitID);
		return null;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return deliverDataService.clear();
		return null;
	}

	@Override
	public ArrayList<DeliverPO> getAll() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return deliverDataService.getAll();
		return null;
	}

	@Override
	public Connection getConn() throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<String> available(String HallID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return deliverDataService.available(HallID);
		return null;
	}

	@Override
	public ArrayList<String> searchAsPerson(String ID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return deliverDataService.searchAsPerson(ID);
		return null;
	}

	@Override
	public List<DeliverPO> getHistory(String creatorID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return deliverDataService.getHistory(creatorID);
		return null;
	}

}
