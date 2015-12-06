package vo.financevo;

import java.util.ArrayList;
import java.util.Calendar;

import po.FormEnum;
import po.FormStateEnum;
import po.financedata.RevenuePO;
import vo.FormVO;

/**
 * Created by Sissel on 2015/10/24.
 */
public class RevenueVO extends FinanceFormVO {
    public Calendar date;
    public String amount;
    public String deliverName;
    public String hallID;
    private ArrayList<String> orderIDs;
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
			String hallID, ArrayList<String> orderIDs) {
		this(formID);
		this.date = date;
		this.amount = amount;
		this.deliverName = deliverName;
		this.hallID = hallID;
		this.orderIDs = orderIDs;
	}
    public RevenueVO(RevenuePO po){
    	this(po.getFormID(),po.getDate(), po.getAmount(), po.getDeliverName(), po.getHallID(), po.getOrderIDs());
    }
    //
    public RevenuePO toPO(){
    	return new RevenuePO(formID, (Calendar)date.clone(), amount, deliverName, hallID, orderIDs);
    }
	/* (non-Javadoc)
	 * @see vo.FormVO#getMainInfo()
	 */
	@Override
	public String getMainInfo() {
		return "快递员: "+deliverName+" 合计  "+amount;
	}
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
	public ArrayList<String> getOrderIDs() {
		return orderIDs;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public void setDeliverName(String deliverName) {
		this.deliverName = deliverName;
	}
	public void setHallID(String hallID) {
		this.hallID = hallID;
	}

}
