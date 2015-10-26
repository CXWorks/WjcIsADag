package rmi.financedata;

import message.OperationMessage;
import model.bankAccount.BankAccountOperation;
import po.financedata.BankAccountPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/23.
 */
public interface BankAccountDataService extends Remote {

    public String getNewBankID() throws RemoteException;

    public OperationMessage checkIsNameUsed(String name) throws RemoteException;

    public BankAccountPO getBankAccount(String bankID) throws RemoteException;

    public LinkedList<BankAccountOperation> updateAccountOperations(String staffID) throws RemoteException;

    public OperationMessage uploadAccountOperations
            (String staffID, LinkedList<BankAccountOperation> operations) throws RemoteException;

    public LinkedList<BankAccountPO> downloadAllAccounts() throws RemoteException;

}
