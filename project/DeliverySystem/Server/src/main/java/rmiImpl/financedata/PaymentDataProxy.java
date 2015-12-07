package rmiImpl.financedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;

import message.OperationMessage;
import po.financedata.PaymentPO;
import rmi.financedata.PaymentDataService;
import rmiImpl.initaldata.InitialDataProxy;

public class PaymentDataProxy extends UnicastRemoteObject implements PaymentDataService {

	PaymentDataService paymentDataService = new PaymentDataImpl();

	public PaymentDataProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public OperationMessage insert(PaymentPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return paymentDataService.insert(po);
		return null;
	}

	@Override
	public PaymentPO getFormPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return paymentDataService.getFormPO(id);
		return null;
	}

	@Override
	public OperationMessage delete(String id) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return paymentDataService.delete(id);
		return null;
	}

	@Override
	public OperationMessage update(PaymentPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return paymentDataService.update(po);
		return null;
	}

	@Override
	public String newID(String unitID) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return paymentDataService.newID(unitID);
		return null;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return paymentDataService.clear();
		return null;
	}

	@Override
	public ArrayList<PaymentPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return paymentDataService.getAll();
		return null;
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PaymentPO> getByTime(Calendar start, Calendar end) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return paymentDataService.getByTime(start, end);
		return null;
	}

}
