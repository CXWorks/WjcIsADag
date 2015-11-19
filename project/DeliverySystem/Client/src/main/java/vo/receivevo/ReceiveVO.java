package vo.receivevo;

import java.util.Calendar;

import po.FormEnum;
import po.receivedata.ReceivePO;
import po.receivedata.StateEnum;
import vo.FormVO;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class ReceiveVO extends FormVO{
	public ReceiveVO(){
		super(FormEnum.RECEIVE);
	}
	//
	public ReceiveVO(ReceivePO po){
		this(po.getOrderID(), po.getTransitID(), po.getDate(), po.getDepature(), po.getState());
	}
	public ReceiveVO(String orderID, String transitID, Calendar date,
			String depature, StateEnum state) {
		this();
		this.orderID = orderID;
		this.transitID = transitID;
		this.date = date;
		this.depature = depature;
		this.state = state;
	}
	
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
	
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
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
}
