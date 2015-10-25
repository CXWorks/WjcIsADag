package rmi.financedata;

import message.OperationMessage;
import model.bankAccount.BankAccountOperation;
import po.financedata.BankAccountPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Sissel on 2015/10/23.
 */
public interface BankAccountDataService extends Remote {

    public String getNewBankID() throws RemoteException;

    public OperationMessage checkIsNameUsed(String name) throws RemoteException;

    public BankAccountPO getBankAccount(String bankID) throws RemoteException;

    public List<BankAccountOperation> updateAccountOperations(String staffID) throws RemoteException;

    public OperationMessage uploadAccountOperations
            (String staffID, List<BankAccountOperation> operations) throws RemoteException;

    public List<BankAccountPO> downloadAllAccounts() throws RemoteException;

}
