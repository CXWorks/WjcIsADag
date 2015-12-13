package bl.blService.financeblService;

import message.OperationMessage;
import vo.financevo.BankAccountVO;
import vo.financevo.PaymentVO;

import java.util.List;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface BankAccountBLService {

    public List<BankAccountVO> getAllAccounts();

    public List<BankAccountVO> filterAccounts(List<BankAccountVO> list, String s);

    public OperationMessage addAccount(BankAccountVO avo);

    public OperationMessage deleteAccount(BankAccountVO avo);

    public OperationMessage editAccount(BankAccountVO avo, String newName);

    public List<PaymentVO> getTradeHistory(BankAccountVO avo);

    public OperationMessage pay(String bankAccID, String amount);

    public OperationMessage receive(String bankAccID, String amount);
    
    public String newBankID();

}
