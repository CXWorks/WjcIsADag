package vo.financevo;

import vo.FormVO;

/**
 * Created by Sissel on 2015/10/24.
 */
public class PaymentVO extends FormVO {
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
