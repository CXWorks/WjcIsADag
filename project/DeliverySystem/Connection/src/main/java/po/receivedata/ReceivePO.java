package po.receivedata;

import java.io.Serializable;
import java.util.Calendar;

import po.FormPO;
import po.receivedata.StateEnum;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class ReceivePO extends FormPO implements Serializable{
	private String orderID;
	private String transitID;
	private Calendar date;
	private String depature;
	private StateEnum state;
	
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getTransitID() {
		return transitID;
	}
	public void setTransitID(String transitID) {
		this.transitID = transitID;
	}
	public String getDepature() {
		return depature;
	}
	public void setDepature(String depature) {
		this.depature = depature;
	}
	public StateEnum getState() {
		return state;
	}
	public void setState(StateEnum state) {
		this.state = state;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	
	//
	
}
