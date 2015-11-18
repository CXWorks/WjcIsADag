package vo.financevo;

import po.InfoEnum;
import po.financedata.BankAccountPO;
import vo.InfoVO;

/**
 * Created by Sissel on 2015/10/24.
 */
public class BankAccountVO extends InfoVO{
    private String bankID;
    private String accountName;
    private String balance;

    public String getBankID() {
        return bankID;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getBalance() {
        return balance;
    }
    public BankAccountVO(){
    	super(InfoEnum.BANK_ACCOUNT);
    }
    //

	/**
	 * @param bankID
	 * @param accountName
	 * @param balance
	 */
	public BankAccountVO(String bankID, String accountName, String balance) {
		this();
		this.bankID = bankID;
		this.accountName = accountName;
		this.balance = balance;
	}
    public BankAccountVO(BankAccountPO po){
    	this(po.getBankID(), po.getAccountName(),po.getBalance() );
    }
}
