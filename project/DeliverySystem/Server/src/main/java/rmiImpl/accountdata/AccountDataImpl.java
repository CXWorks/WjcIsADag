package rmiImpl.accountdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import message.OperationMessage;
import po.accountdata.AccountPO;
import po.companydata.CenterPO;
import rmi.accountdata.AccountDataService;

/**
 * 
 * @author mx
 *2015/10/25
 */

public class AccountDataImpl extends UnicastRemoteObject implements AccountDataService{

	public AccountDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountPO getAccountPO(String accountID) {
		// TODO Auto-generated method stub
		return new AccountPO();
	}

	public ArrayList<AccountPO> getAccountPOs() {
		// TODO Auto-generated method stub
		ArrayList<AccountPO> result=new ArrayList<AccountPO>();
		AccountPO stub=new AccountPO();
		result.add(stub);
		return result;
	}

	public OperationMessage insert(AccountPO po) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage delete(String accountID) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage update(AccountPO po) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public Boolean checkID(String accountID) {
		// TODO Auto-generated method stub
		return true;
	}

	public Boolean checkAccount(String id, String password) {
		// TODO Auto-generated method stub
		return true;
	}

	public String newAccountID() {
		// TODO Auto-generated method stub
		return "00001";
	}

}
