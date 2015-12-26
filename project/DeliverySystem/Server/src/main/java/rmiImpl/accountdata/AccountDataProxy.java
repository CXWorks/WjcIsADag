package rmiImpl.accountdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;

import database.RMIHelper;
import message.OperationMessage;
import po.accountdata.AccountPO;
import po.systemdata.LogPO;
import po.systemdata.SystemState;
import rmi.accountdata.AccountDataService;
import rmiImpl.initaldata.InitialDataProxy;

public class AccountDataProxy extends UnicastRemoteObject implements AccountDataService {

	private AccountDataService accountDataService = new AccountDataImpl();

	public AccountDataProxy() throws RemoteException {
		super();
	}

	@Override
	public Connection getConn() throws RemoteException {
		return null;
	}

	@Override
	public AccountPO getAccountPO(String accountID) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return accountDataService.getAccountPO(accountID);
		return null;
	}

	@Override
	public ArrayList<AccountPO> getAccountPOs() throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return accountDataService.getAccountPOs();
		return null;
	}

	@Override
	public OperationMessage insert(AccountPO po) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			OperationMessage message = accountDataService.insert(po);
			// 系统日志
			if (message.operationResult == true)
				RMIHelper.getLogDataService().insert(new LogPO("管理员", Calendar.getInstance(), "新建账号:" + po.getID()));
			return message;
		}
		return null;
	}

	@Override
	public OperationMessage delete(String accountID) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)){
			OperationMessage message = accountDataService.delete(accountID);
			// 系统日志
			if (message.operationResult == true)
				RMIHelper.getLogDataService().insert(new LogPO("管理员", Calendar.getInstance(), "删除账号:" + accountID));

			return message;
		}
		return null;
	}

	@Override
	public OperationMessage update(AccountPO po) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return accountDataService.update(po);
		return null;
	}

	@Override
	public OperationMessage checkAccount(String id, String password) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return accountDataService.checkAccount(id, password);
		return new OperationMessage(false, "system is initializing");
	}

	@Override
	public OperationMessage setAccount(String id, boolean isOnline) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return accountDataService.setAccount(id, isOnline);
		return null;
	}

}
