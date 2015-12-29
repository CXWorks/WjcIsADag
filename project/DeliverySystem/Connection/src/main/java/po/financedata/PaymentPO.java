package po.financedata;

import po.FormEnum;
import po.FormPO;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by Sissel on 2015/10/23.
 */
public class PaymentPO extends FormPO {
    private Calendar	date;
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

	public Calendar getDate() {
		return date;
	}
	public void setPayerAccID(String payerAccID) {
		this.payerAccID = payerAccID;
	}
	public void setReceiverAccID(String receiverAccID) {
		this.receiverAccID = receiverAccID;
	}
	public Timestamp getDateForSQL() {
		return new Timestamp(this.date.getTimeInMillis());
	}
	public String getAmount() {
		return amount;
	}
	public String getPayerAccID() {
		return payerAccID;
	}
	public String getPayerName() {
		return payerName;
	}
	public String getPayerAccount() {
		return payerAccount;
	}
	public String getReceiverAccID() {
		return receiverAccID;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public String getReceiverAccount() {
		return receiverAccount;
	}
	public FinancePayEnum getItem() {
		return item;
	}
	public String getNote() {
		return note;
	}
	public String getItemForSQL(){
		return item.name();
	}
	/**
	 * @param formEnum
	 * @param formID
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
	public PaymentPO(String formID, Calendar date,
			String amount, String payerAccID, String payerName,
			String payerAccount, String receiverAccID, String receiverName,
			String receiverAccount, FinancePayEnum item, String note,String creatorID) {
		super(FormEnum.PAYMENT, formID,creatorID);
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
	public PaymentPO(String formID, Timestamp date,
			String amount, String payerAccID, String payerName,
			String payerAccount, String receiverAccID, String receiverName,
			String receiverAccount, String item, String note,String creatorID) {
		super(FormEnum.PAYMENT, formID,creatorID);
		Calendar temp = Calendar.getInstance();
		temp.setTime(date);
		this.date = temp;
		this.amount = amount;
		this.payerAccID = payerAccID;
		this.payerName = payerName;
		this.payerAccount = payerAccount;
		this.receiverAccID = receiverAccID;
		this.receiverName = receiverName;
		this.receiverAccount = receiverAccount;
		this.item = this.setItem(item);
		this.note = note;
	}
	//
	private FinancePayEnum setItem(String item){
		switch (item) {
		case "RENT":
			return FinancePayEnum.RENT;
		case "TRANSPORTION":
			return FinancePayEnum.TRANSPORTION;
		case "SALARY":
			return FinancePayEnum.SALARY;
		case "AWARD":
			return FinancePayEnum.AWARD;

		default:
			return null;
		}
	}

    //

}
