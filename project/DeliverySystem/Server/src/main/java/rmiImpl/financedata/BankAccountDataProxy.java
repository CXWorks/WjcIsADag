package rmiImpl.financedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
import po.FormPO;
import po.financedata.BankAccountPO;
import po.systemdata.SystemState;
import rmi.financedata.BankAccountDataService;
import rmiImpl.initaldata.InitialDataProxy;

public class BankAccountDataProxy extends UnicastRemoteObject implements BankAccountDataService {

	BankAccountDataService bankAccountDataService = new BankAccountDataImpl();

	public BankAccountDataProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNewBankID() throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return bankAccountDataService.getNewBankID();
		return null;
	}

	@Override
	public OperationMessage checkIsNameUsed(String name) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return bankAccountDataService.checkIsNameUsed(name);
		return null;
	}

	@Override
	public BankAccountPO getBankAccount(String bankID) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return bankAccountDataService.getBankAccount(bankID);
		return null;
	}

	@Override
	public OperationMessage insert(BankAccountPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return bankAccountDataService.insert(po);
		return null;
	}

	@Override
	public OperationMessage delete(String id) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return bankAccountDataService.delete(id);
		return null;
	}

	@Override
	public OperationMessage update(BankAccountPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return bankAccountDataService.update(po);
		return null;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return bankAccountDataService.clear();
		return null;
	}

	@Override
	public ArrayList<BankAccountPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return bankAccountDataService.getAll();
		return null;
	}

	@Override
	public ArrayList<FormPO> getByAccID(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return bankAccountDataService.getByAccID(ID);
		return null;
	}

}
