package vo.financevo;

import java.util.Calendar;

import po.FormEnum;
import po.FormStateEnum;
import po.financedata.FinancePayEnum;
import po.financedata.PaymentPO;
import vo.FormVO;

/**
 * Created by Sissel on 2015/10/24.
 */
public class PaymentVO extends FormVO {
    private Calendar date;
    private String	amount;
    private String	payerAccID;
    private String	payerName;
    private String	payerAccount;
    private String	receiverAccID;
    private String	receiverName;
    private String	receiverAccount;
    // 条目
    private FinancePayEnum	item;
    // 备注
    private String	note;
    //
    public PaymentVO(String formID){
    	super(FormEnum.PAYMENT,FormStateEnum.CONSTRUCTED,formID);
    }
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
	public PaymentVO(String formID,Calendar date, String amount, String payerAccID,
			String payerName, String payerAccount, String receiverAccID,
			String receiverName, String receiverAccount, FinancePayEnum item,
			String note) {
		this(formID);
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
    	this(po.getFormID(),po.getDate(), po.getAmount(), po.getPayerAccID(), po.getPayerName(), po.getPayerAccount(), po.getReceiverAccID(), po.getReceiverName(), po.getReceiverAccount(), po.getItem(), po.getNote());
    	
    }
    //
    public PaymentPO toPO(){
    	return new PaymentPO(amount, (Calendar)date.clone(), amount, payerAccID, payerName, payerAccount, receiverAccID, receiverName, receiverAccount, item, note);
    }
}
