package blImpl.financebl;

import blService.financeblService.BankAccountBLService;
import message.OperationMessage;
import rmi.financedata.BankAccountDataService;
import util.R;
import vo.financevo.BankAccountVO;
import vo.financevo.PaymentVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/26.
 */
public class BankAccountBLImpl implements BankAccountBLService {
    public List<BankAccountVO> getAllAccounts() {
        return new LinkedList<BankAccountVO>();
    }

    public List<BankAccountVO> filterAccounts(List<BankAccountVO> list, String s) {
        return new LinkedList<BankAccountVO>();
    }

    public OperationMessage addAccount(BankAccountVO avo) {
        return new OperationMessage();
    }

    public OperationMessage deleteAccount(BankAccountVO avo) {
        return new OperationMessage();
    }

    public OperationMessage editAccount(BankAccountVO avo, String newName) {
        return new OperationMessage();
    }

    public List<PaymentVO> getTradeHistory(BankAccountVO avo) {
        return new LinkedList<PaymentVO>();
    }

    public OperationMessage pay(String bankAccID, String amount) {
        return new OperationMessage();
    }

    public OperationMessage receive(String bankAccID, String amount) {
        return new OperationMessage();
    }

    public static void drive(BankAccountDataService bads) throws RemoteException{
        if(bads.checkIsNameUsed("345") != null)
            System.out.println("test checkIsNameUsed");
        if(bads.downloadAllAccounts() != null)
            System.out.println("test downloadAllAccounts");
        if(bads.getBankAccount("20150730") != null)
            System.out.println("test getBankAccount");
        if(bads.getNewBankID() != null)
            System.out.println("test getNewBankID");
        if(bads.updateAccountOperations("9527") != null)
            System.out.println("test updateAccountOperations");
        if(bads.uploadAccountOperations("ddd" , null) != null)
            System.out.println("test uploadAccountOperations");
    }

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        BankAccountDataService bads = (BankAccountDataService)Naming.lookup
                ("rmi://" + R.string.LocalHost + "/" + R.string.FinanceDataService);
        drive(bads);
    }
}
