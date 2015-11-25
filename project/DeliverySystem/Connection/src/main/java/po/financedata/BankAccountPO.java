package po.financedata;

import java.io.Serializable;

import po.CommonPO;
import po.InfoEnum;
import po.InfoPO;

/**
 * Created by Sissel on 2015/10/23.
 */
public class BankAccountPO extends InfoPO implements Serializable {
    private String bankID;
    private String accountName;
    private String balance;
    public void balanceOut(String money){
    	double m=Double.parseDouble(money);
    	double b=Double.parseDouble(balance);
    	b=b-m;
    	this.balance=Double.toString(b);
    }
    //
    public void balanceIn(String money){
    	double m=Double.parseDouble(money);
    	double b=Double.parseDouble(balance);
    	b=b+m;
    	this.balance=Double.toString(b);
    }
    /**
	 * @param infoEnum
	 * @param bankID
	 * @param accountName
	 * @param balance
	 */
	public BankAccountPO(String bankID, String accountName,
			String balance) {
		super(InfoEnum.BANK_ACCOUNT);
		this.bankID = bankID;
		this.accountName = accountName;
		this.balance = balance;
	}

	public String getBankID() {
        return bankID;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getBalance() {
        return balance;
    }

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
    
}
