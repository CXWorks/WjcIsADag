package po.financedata;

import java.util.Calendar;

import po.CommonPO;
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
    
    //
    
}
