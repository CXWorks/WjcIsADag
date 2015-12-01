package model.bankAccount;

import java.io.Serializable;

/**
 * Created by Sissel on 2015/10/23.
 */
public class BankAccountOperation implements Serializable{
    enum BankAccountOperationType {
        ADD_ACC,
        DELETE_ACC,
        EDIT_ACC_NAME,
        PAY,
        RECEIVE;
    }

    private BankAccountOperationType type;
    private String bankID;
    String para_name;
    String para_balance;
}
