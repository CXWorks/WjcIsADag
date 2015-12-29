package vo.financevo;

import java.util.Calendar;

import po.FormEnum;
import po.FormStateEnum;
import po.financedata.FinancePayEnum;
import po.financedata.PaymentPO;
import userinfo.UserInfo;
import vo.FormVO;

/**
 * Created by Sissel on 2015/10/24.
 */
public class PaymentVO extends FinanceFormVO {
    public Calendar date;
    public String	amount;
    public String	payerAccID;
    public String	payerName;
    public String	payerAccount;
    public String	receiverAccID;
    public String	receiverName;
    public String	receiverAccount;
    // 条目
    private FinancePayEnum	item;
    // 备注
    private String	note;
    //
    public PaymentVO(String formID,String createrID){
    	super(FormEnum.PAYMENT,FormStateEnum.CONSTRUCTED,formID,createrID);
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
			String note,String createrID) {
		this(formID,createrID);
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
    	this(po.getFormID(),po.getDate(), po.getAmount(), po.getPayerAccID(), po.getPayerName(), po.getPayerAccount(), po.getReceiverAccID(), po.getReceiverName(), po.getReceiverAccount(), po.getItem(), po.getNote(),po.getCreatorID());

    }
    //
    public PaymentPO toPO(){
    	PaymentPO paymentPO= new PaymentPO(formID, (Calendar)date.clone(), amount, payerAccID, payerName, payerAccount, receiverAccID, receiverName, receiverAccount, item, note,createrID);
    	paymentPO.setCache_OperatorID(UserInfo.getUserID());
    	return paymentPO;
    }
	/* (non-Javadoc)
	 * @see vo.FormVO#getMainInfo()
	 */
	@Override
	public String getMainInfo() {
		return amount+" from "+payerName+" to "+receiverName;
	}
}
