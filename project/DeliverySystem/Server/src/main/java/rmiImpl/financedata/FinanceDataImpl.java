package rmiImpl.financedata;

import message.OperationMessage;
import model.bankAccount.BankAccountOperation;
import po.financedata.BankAccountPO;
import po.financedata.PaymentPO;
import po.financedata.RevenuePO;
import rmi.financedata.BankAccountDataService;
import rmi.financedata.FinanceFormDataService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class FinanceDataImpl extends UnicastRemoteObject implements BankAccountDataService, FinanceFormDataService{

    protected FinanceDataImpl() throws RemoteException, MalformedURLException {
        Naming.rebind("FinanceDataService", this);
    }

    public static void main(String[] args) throws MalformedURLException, RemoteException {
        new FinanceDataImpl();
    }

    public String getNewBankID() {
        // TODO Auto-generated method stub
        return "222333";
    }

    public OperationMessage checkIsNameUsed(String name) {
        // TODO Auto-generated method stub
        return new OperationMessage();
    }

    public List<BankAccountOperation> updateAccountOperations(String staffID) {
        // TODO Auto-generated method stub
        return new LinkedList<BankAccountOperation>();
    }

    public OperationMessage uploadAccountOperations(String staffID, List<BankAccountOperation> operations) {
        // TODO Auto-generated method stub
        return new OperationMessage();
    }

    public List<BankAccountPO> downloadAllAccounts() {
        // TODO Auto-generated method stub
        return new LinkedList<BankAccountPO>();
    }

	public BankAccountPO getBankAccount(String bankID) {
		// TODO Auto-generated method stub
		return new BankAccountPO();
	}


    public String getNewRevenueID(String date, String hallID) {
        // TODO Auto-generated method stub
        return "222333";
    }

    public String getNewPaymentID(String date) {
        // TODO Auto-generated method stub
        return "222333";
    }

    public RevenuePO getRevenuePO(String formID) {
        // TODO Auto-generated method stub
        return new RevenuePO();
    }

    public PaymentPO getPaymentPO(String formID) {
        // TODO Auto-generated method stub
        return new PaymentPO();
    }

    public List<RevenuePO> updateRevenuePOs(String staffID) {
        // TODO Auto-generated method stub
        return new LinkedList<RevenuePO>();
    }

    public List<PaymentPO> updatePaymentPOs(String staffID) {
        // TODO Auto-generated method stub
        return new LinkedList<PaymentPO>();
    }

    public List<PaymentPO> downloadAllPaymentPOs() {
        // TODO Auto-generated method stub
        return new LinkedList<PaymentPO>();
    }

    public List<RevenuePO> downloadAllRevenuePOs() {
        // TODO Auto-generated method stub
        return new LinkedList<RevenuePO>();
    }
}
