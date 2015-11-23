package vo.financevo;

import java.util.Calendar;

import po.FormEnum;
import po.FormStateEnum;
import po.financedata.RevenuePO;
import vo.FormVO;

/**
 * Created by Sissel on 2015/10/24.
 */
public class RevenueVO extends FormVO {
    private Calendar date;
    private String amount;
    private String deliverName;
    private String hallID;
    private String orderID;
    //
    public RevenueVO(String formID){
    	super(FormEnum.REVENUE,FormStateEnum.CONSTRUCTED,formID);
    }
    //
	/**
	 * @param date
	 * @param amount
	 * @param deliverName
	 * @param hallID
	 * @param orderID
	 */
	public RevenueVO(String formID,Calendar date, String amount, String deliverName,
			String hallID, String orderID) {
		this(formID);
		this.date = date;
		this.amount = amount;
		this.deliverName = deliverName;
		this.hallID = hallID;
		this.orderID = orderID;
	}
    public RevenueVO(RevenuePO po){
    	this(po.getFormID(),po.getDate(), po.getAmount(), po.getDeliverName(), po.getHallID(), po.getOrderID());
    }
    //
    public RevenuePO toPO(){
    	return new RevenuePO(amount, (Calendar)date.clone(), amount, deliverName, hallID, orderID);
    }
}
