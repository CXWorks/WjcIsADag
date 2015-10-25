package rmiImpl.financedata;

import message.OperationMessage;
import model.bankAccount.BankAccountOperation;
import po.financedata.BankAccountPO;
import rmi.financedata.BankAccountDataService;

import java.util.List;

public class FinanceDataImpl implements BankAccountDataService{



    public List<BankAccountOperation> updateAccountOperations(String staffID) {
        return null;
    }

    public OperationMessage uploadAccountOperations(String staffID, List<BankAccountOperation> operations) {
        return null;
    }

    public List<BankAccountPO> downloadAllAccounts() {
        return null;
    }

	public BankAccountPO getBankAccount(String bankID) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNewBankID() {
		// TODO Auto-generated method stub
		return null;
	}


}
