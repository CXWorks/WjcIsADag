package po.financedata;

import java.io.Serializable;

import po.CommonPO;
import po.InfoPO;

/**
 * Created by Sissel on 2015/10/23.
 */
public class BankAccountPO extends InfoPO implements Serializable {
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
}
