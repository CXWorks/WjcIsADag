package rmi.financedata;

import message.OperationMessage;
import model.bankAccount.BankAccountOperation;
import po.financedata.BankAccountPO;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by Sissel on 2015/10/23.
 */
public interface BankAccountDataService extends Remote {

    public String getNewBankID();

    public BankAccountPO getBankAccount(String bankID);

    public List<BankAccountOperation> updateAccountOperations(String staffID);

    public OperationMessage uploadAccountOperations
            (String staffID, List<BankAccountOperation> operations);

    public List<BankAccountPO> downloadAllAccounts();

}
