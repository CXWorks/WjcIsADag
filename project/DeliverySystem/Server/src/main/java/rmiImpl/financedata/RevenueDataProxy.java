package rmiImpl.financedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;

import message.OperationMessage;
import po.financedata.RevenuePO;
import po.systemdata.SystemState;
import rmi.financedata.RevenueDataService;
import rmiImpl.initaldata.InitialDataProxy;

public class RevenueDataProxy extends UnicastRemoteObject implements RevenueDataService {

	RevenueDataService revenueDataService = new RevenueDataImpl();

	public RevenueDataProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public OperationMessage insert(RevenuePO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.insert(po);
		return null;
	}

	@Override
	public RevenuePO getFormPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.getFormPO(id);
		return null;
	}

	@Override
	public OperationMessage delete(String id) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.delete(id);
		return null;
	}

	@Override
	public OperationMessage update(RevenuePO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.update(po);
		return null;
	}

	@Override
	public String newID(String unitID) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.newID(unitID);
		return null;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.clear();
		return null;
	}

	@Override
	public ArrayList<RevenuePO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.getAll();
		return null;
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<RevenuePO> getByHallID(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.getByHallID(ID);
		return null;
	}

	@Override
	public ArrayList<RevenuePO> getByTime(Calendar start, Calendar end) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return revenueDataService.getByTime(start, end);
		return null;
	}

}
