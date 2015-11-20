package po.deliverdata;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import po.FormPO;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class DeliverPO extends FormPO implements Serializable{
	
	private String orderID;
	private Timestamp date;
	private String postman;
	
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
	public Timestamp getDate() {
		return date;
	}
	
	public void setData(Timestamp data) {
		this.date = data;
	}
}
