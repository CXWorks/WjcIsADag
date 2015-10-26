package rmiImpl.financedata;

import message.OperationMessage;
import model.bankAccount.BankAccountOperation;
import po.financedata.BankAccountPO;
import po.financedata.PaymentPO;
import po.financedata.RevenuePO;
import rmi.financedata.BankAccountDataService;
import rmi.financedata.FinanceFormDataService;
import util.R;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class FinanceDataImpl extends UnicastRemoteObject implements BankAccountDataService, FinanceFormDataService{

    protected FinanceDataImpl() throws RemoteException, MalformedURLException {
        Naming.rebind(R.string.FinanceDataService, this);
    }

    public static void main(String[] args) throws MalformedURLException, RemoteException {
        LocateRegistry.createRegistry(1099);
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

    public LinkedList<BankAccountOperation> updateAccountOperations(String staffID) {
        // TODO Auto-generated method stub
        return new LinkedList<BankAccountOperation>();
    }

    public OperationMessage uploadAccountOperations(String staffID, List<BankAccountOperation> operations) {
        // TODO Auto-generated method stub
        return new OperationMessage();
    }

    public LinkedList<BankAccountPO> downloadAllAccounts() {
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

    public LinkedList<RevenuePO> updateRevenuePOs(String staffID) {
        // TODO Auto-generated method stub
        return new LinkedList<RevenuePO>();
    }

    public LinkedList<PaymentPO> updatePaymentPOs(String staffID) {
        // TODO Auto-generated method stub
        return new LinkedList<PaymentPO>();
    }

    public LinkedList<PaymentPO> downloadAllPaymentPOs() {
        // TODO Auto-generated method stub
        return new LinkedList<PaymentPO>();
    }

    public LinkedList<RevenuePO> downloadAllRevenuePOs() {
        // TODO Auto-generated method stub
        return new LinkedList<RevenuePO>();
    }
}
