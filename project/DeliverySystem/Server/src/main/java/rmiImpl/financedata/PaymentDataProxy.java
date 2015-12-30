package rmiImpl.financedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import message.OperationMessage;
import po.financedata.PaymentPO;
import po.systemdata.SystemState;
import rmi.financedata.PaymentDataService;
import rmiImpl.initaldata.InitialDataProxy;

public class PaymentDataProxy extends UnicastRemoteObject implements PaymentDataService {

	PaymentDataService paymentDataService = new PaymentDataImpl();

	public PaymentDataProxy() throws RemoteException {
		super();
	}

	@Override
	public OperationMessage insert(PaymentPO po) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return paymentDataService.insert(po);
		return null;
	}

	@Override
	public PaymentPO getFormPO(String id) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return paymentDataService.getFormPO(id);
		return null;
	}

	@Override
	public OperationMessage delete(String id) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return paymentDataService.delete(id);
		return null;
	}

	@Override
	public OperationMessage update(PaymentPO po) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return paymentDataService.update(po);
		return null;
	}

	@Override
	public String newID(String unitID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return paymentDataService.newID(unitID);
		return null;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return paymentDataService.clear();
		return null;
	}

	@Override
	public ArrayList<PaymentPO> getAll() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return paymentDataService.getAll();
		return null;
	}

	@Override
	public Connection getConn() throws RemoteException {
		return null;
	}

	@Override
	public ArrayList<PaymentPO> getByTime(Calendar start, Calendar end) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return paymentDataService.getByTime(start, end);
		return null;
	}

	@Override
	public List<PaymentPO> getHistory(String creatorID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return paymentDataService.getHistory(creatorID);
		return null;
	}

}
