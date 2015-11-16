package vo.financevo;

/**
 * Created by Sissel on 2015/10/24.
 */
public class BankAccountVO {
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
    public BankAccountVO(){}
    //

	/**
	 * @param bankID
	 * @param accountName
	 * @param balance
	 */
	public BankAccountVO(String bankID, String accountName, String balance) {
		super();
		this.bankID = bankID;
		this.accountName = accountName;
		this.balance = balance;
	}
    
}
