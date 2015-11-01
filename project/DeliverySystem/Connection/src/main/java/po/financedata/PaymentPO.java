package po.financedata;

import po.CommonPO;
import po.FormPO;

import java.io.Serializable;

/**
 * Created by Sissel on 2015/10/23.
 */
public class PaymentPO extends FormPO {
    private String	date;
    private String	amount;
    private String	payerAccID;
    private String	payerName;
    private String	payerAccount;
    private String	receiverAccID;
    private String	receiverName;
    private String	receiverAccount;
    // 条目
    private String	item;
    // 备注
    private String	note;
}
