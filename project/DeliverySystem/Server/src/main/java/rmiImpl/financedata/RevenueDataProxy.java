package rmiImpl.financedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import message.OperationMessage;
import po.financedata.RevenuePO;
import po.systemdata.SystemState;
import rmi.financedata.RevenueDataService;
import rmiImpl.initaldata.InitialDataProxy;

public class RevenueDataProxy extends UnicastRemoteObject implements RevenueDataService {

	RevenueDataService revenueDataService = new RevenueDataImpl();

	public RevenueDataProxy() throws RemoteException {
		super();
	}

	@Override
	public OperationMessage insert(RevenuePO po) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.insert(po);
		return null;
	}

	@Override
	public RevenuePO getFormPO(String id) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.getFormPO(id);
		return null;
	}

	@Override
	public OperationMessage delete(String id) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.delete(id);
		return null;
	}

	@Override
	public OperationMessage update(RevenuePO po) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.update(po);
		return null;
	}

	@Override
	public String newID(String unitID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.newID(unitID);
		return null;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.clear();
		return null;
	}

	@Override
	public ArrayList<RevenuePO> getAll() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.getAll();
		return null;
	}

	@Override
	public ArrayList<RevenuePO> getByHallID(String ID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.getByHallID(ID);
		return null;
	}

	@Override
	public ArrayList<RevenuePO> getByTime(Calendar start, Calendar end) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.getByTime(start, end);
		return null;
	}

	@Override
	public List<RevenuePO> getHistory(String creatorID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.getHistory(creatorID);
		return null;
	}

}
