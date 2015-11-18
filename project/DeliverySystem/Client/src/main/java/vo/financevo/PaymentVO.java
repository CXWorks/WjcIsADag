package vo.financevo;

import java.util.Calendar;

import po.financedata.PaymentPO;
import vo.FormVO;

/**
 * Created by Sissel on 2015/10/24.
 */
public class PaymentVO extends FormVO {
    private Calendar	date;
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
    //
    public PaymentVO(){}
    //
	/**
	 * @param date
	 * @param amount
	 * @param payerAccID
	 * @param payerName
	 * @param payerAccount
	 * @param receiverAccID
	 * @param receiverName
	 * @param receiverAccount
	 * @param item
	 * @param note
	 */
	public PaymentVO(Calendar date, String amount, String payerAccID,
			String payerName, String payerAccount, String receiverAccID,
			String receiverName, String receiverAccount, String item,
			String note) {
		super();
		this.date = date;
		this.amount = amount;
		this.payerAccID = payerAccID;
		this.payerName = payerName;
		this.payerAccount = payerAccount;
		this.receiverAccID = receiverAccID;
		this.receiverName = receiverName;
		this.receiverAccount = receiverAccount;
		this.item = item;
		this.note = note;
	}
    public PaymentVO(PaymentPO po){
    	this(po.getDate(), po.getAmount(), po.getPayerAccID(), po.getPayerName(), po.getPayerAccount(), po.getReceiverAccID(), po.getReceiverName(), po.getReceiverAccount(), po.getItem(), po.getNote());
    	
    }
}
