package rmi.financedata;

import message.OperationMessage;
import model.bankAccount.BankAccountOperation;
import po.CommonPO;
import po.FormPO;
import po.financedata.BankAccountPO;
import rmi.DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/23.
 */
public interface BankAccountDataService extends DataService<CommonPO>{

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "BankAccountData";

    public String getNewBankID() throws RemoteException;

    public OperationMessage checkIsNameUsed(String name) throws RemoteException;

    public BankAccountPO getBankAccount(String bankID) throws RemoteException;

    public OperationMessage insert(BankAccountPO po) throws RemoteException;

    public OperationMessage delete(String id) throws RemoteException;

    public OperationMessage update(BankAccountPO po) throws RemoteException;

    public OperationMessage clear() throws RemoteException;

    public ArrayList<BankAccountPO> getAll() throws RemoteException;

    public ArrayList<FormPO> getByAccID(String ID) throws RemoteException;

    public OperationMessage modifyBalance(String ID,double money) throws RemoteException;

    public String getBankIDByName(String name) throws RemoteException;
}
