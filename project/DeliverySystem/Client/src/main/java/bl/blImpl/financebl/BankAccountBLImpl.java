package bl.blImpl.financebl;

import bl.blService.financeblService.BankAccountBLService;
import bl.clientNetCache.CacheHelper;
import message.OperationMessage;
import rmi.financedata.BankAccountDataService;
import tool.vopo.VOPOFactory;
import util.R;
import vo.financevo.BankAccountVO;
import vo.financevo.PaymentVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import po.financedata.BankAccountPO;

/**
 * Created by Sissel on 2015/10/26.
 */
public class BankAccountBLImpl implements BankAccountBLService {
	private BankAccountDataService bankAccountDataService;
	private VOPOFactory vopoFactory;
	public BankAccountBLImpl(){
		this.bankAccountDataService=CacheHelper.getBankAccountDataService();
	}
    public List<BankAccountVO> getAllAccounts() {
        try {
			ArrayList<BankAccountPO> po=bankAccountDataService.getAll();
			ArrayList<BankAccountVO> vo=new ArrayList<BankAccountVO>(po.size());
			for (int i = 0; i < po.size(); i++) {
				BankAccountPO each=po.get(i);
				BankAccountVO temp=(BankAccountVO)vopoFactory.transPOtoVO(each);
				vo.add(temp);
			}
			return vo;
		} catch (RemoteException e) {
			return null;
		}
    }

    public List<BankAccountVO> filterAccounts(List<BankAccountVO> list, String s) {
        LinkedList<BankAccountVO> ans=new LinkedList<BankAccountVO>();
        for (BankAccountVO bankAccountVO : list) {
			if (bankAccountVO.quzzySearch(s)) {
				ans.add(bankAccountVO);
			}
		}
        return ans;
    }

    public OperationMessage addAccount(BankAccountVO avo) {
        BankAccountPO po=(BankAccountPO)vopoFactory.transVOtoPO(avo);
        try {
			return bankAccountDataService.insert(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
    }

    public OperationMessage deleteAccount(BankAccountVO avo) {
    	String ID=avo.getBankID();
        try {
			return bankAccountDataService.delete(ID);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
    }

    public OperationMessage editAccount(BankAccountVO avo, String newName) {
    	BankAccountPO po=(BankAccountPO)vopoFactory.transVOtoPO(avo);
    	po.setAccountName(newName);
        try {
			return bankAccountDataService.update(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
    }

    public List<PaymentVO> getTradeHistory(BankAccountVO avo) {
        return null;
    }

    public OperationMessage pay(String bankAccID, String amount) {
        try {
			BankAccountPO po=bankAccountDataService.getBankAccount(bankAccID);
			po.balanceOut(amount);
			return bankAccountDataService.update(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
    }

    public OperationMessage receive(String bankAccID, String amount) {
    	 try {
 			BankAccountPO po=bankAccountDataService.getBankAccount(bankAccID);
 			po.balanceIn(amount);
 			return bankAccountDataService.update(po);
 		} catch (RemoteException e) {
 			return new OperationMessage(false, "net error");
 		}
    }
}
