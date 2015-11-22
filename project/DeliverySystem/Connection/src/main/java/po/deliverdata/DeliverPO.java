package po.deliverdata;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import po.FormEnum;
import po.FormPO;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class DeliverPO extends FormPO implements Serializable{
	
	private String orderID;
	private Calendar date;
	private String postman;
	public DeliverPO(){}
	
	public DeliverPO(String orderID, Calendar date, String postman) {
		super();
		this.orderID = orderID;
		this.date = date;
		this.postman = postman;
		this.formType = FormEnum.DELIVER;
	}
	
	public DeliverPO(String orderID, Timestamp date, String postman) {
		super();
		this.orderID = orderID;
		Calendar temp=Calendar.getInstance();
		temp.setTime(date);
		this.date = temp;
		this.postman = postman;
		this.formType = FormEnum.DELIVER;
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
	
}
