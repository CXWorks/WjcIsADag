package po.financedata;

import java.sql.Timestamp;
import java.util.Calendar;

import po.CommonPO;
import po.FormEnum;
import po.FormPO;

/**
 * Created by Sissel on 2015/10/23.
 */
public class RevenuePO extends FormPO{
    private Calendar date;
    private String amount;
    private String deliverName;
    private String hallID;
    private String orderID;
    //
	public Calendar getDate() {
		return date;
	}
	public Timestamp getDateForSQL() {
		return new Timestamp(this.date.getTimeInMillis());
	}
	public String getAmount() {
		return amount;
	}
	public String getDeliverName() {
		return deliverName;
	}
	public String getHallID() {
		return hallID;
	}
	public String getOrderID() {
		return orderID;
	}
	/**
	 * @param formEnum
	 * @param formID
	 * @param date
	 * @param amount
	 * @param deliverName
	 * @param hallID
	 * @param orderID
	 */
	public RevenuePO(String formID, Calendar date,
			String amount, String deliverName, String hallID, String orderID) {
		super(FormEnum.REVENUE, formID);
		this.date = date;
		this.amount = amount;
		this.deliverName = deliverName;
		this.hallID = hallID;
		this.orderID = orderID;
	}
	public RevenuePO(String formID, Timestamp date,
			String amount, String deliverName, String hallID, String orderID) {
		super(FormEnum.REVENUE, formID);
		Calendar temp = Calendar.getInstance();
		temp.setTime(date);
		this.date = temp;
		this.amount = amount;
		this.deliverName = deliverName;
		this.hallID = hallID;
		this.orderID = orderID;
	}
    
}
