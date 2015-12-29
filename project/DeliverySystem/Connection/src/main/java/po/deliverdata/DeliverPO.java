package po.deliverdata;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import po.FormEnum;
import po.FormPO;
import po.FormStateEnum;

/**
 *
 * @author wjc
 *2015/10/24
 */

public class DeliverPO extends FormPO implements Serializable{

	private String orderID;
	private Calendar date;
	private String postman;
	private boolean finished;
	private String receivePeople;
	private Calendar receiveDate;

	public void finfished(Calendar receiveCalendar,String signPeople){
		this.receiveDate=receiveCalendar;
		this.receivePeople=signPeople;
		this.finished=true;
	}


	public DeliverPO(String formID,String orderID, Calendar date, String postman,String creatorID) {
		super(FormEnum.DELIVER,formID,creatorID);
		this.orderID = orderID;
		this.date = date;
		this.postman = postman;
		this.formType = FormEnum.DELIVER;
		this.finished=false;
	}

	public DeliverPO(String formID,String orderID, Timestamp date, String postman,String creatorID) {
		super(FormEnum.DELIVER,formID,creatorID);
		this.orderID = orderID;
		Calendar temp=Calendar.getInstance();
		temp.setTime(date);
		this.date = temp;
		this.postman = postman;
		this.formType = FormEnum.DELIVER;
		this.finished=false;
	}

	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getPostman() {
		return postman;
	}
	public void setPostman(String postman) {
		this.postman = postman;
	}
	public Calendar getDate() {
		return date;
	}
	public Timestamp getDateForSQL(){
		return new Timestamp(this.date.getTimeInMillis());
	}

	public int isFinished() {
		if(finished)
			return 1;
		else {
			return 0;
		}
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}


	public String getReceivePeople() {
		return receivePeople;
	}


	public Calendar getReceiveDate() {
		return receiveDate;
	}


}
