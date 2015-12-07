package rmiImpl.accountdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
import po.accountdata.AccountPO;
import rmi.accountdata.AccountDataService;
import rmiImpl.initaldata.InitialDataProxy;

public class AccountDataProxy extends UnicastRemoteObject implements AccountDataService {

	private AccountDataService accountDataService = new AccountDataImpl();

	public AccountDataProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountPO getAccountPO(String accountID) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return accountDataService.getAccountPO(accountID);
		return null;
	}

	@Override
	public ArrayList<AccountPO> getAccountPOs() throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return accountDataService.getAccountPOs();
		return null;
	}

	@Override
	public OperationMessage insert(AccountPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return accountDataService.insert(po);
		return null;
	}

	@Override
	public OperationMessage delete(String accountID) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return accountDataService.delete(accountID);
		return null;
	}

	@Override
	public OperationMessage update(AccountPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return accountDataService.update(po);
		return null;
	}

	@Override
	public OperationMessage checkAccount(String id, String password) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return accountDataService.checkAccount(id,password);
		return null;
	}

}
