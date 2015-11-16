package vo.receivevo;

import po.receivedata.StateEnum;
import vo.FormVO;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class ReceiveVO extends FormVO{
	public ReceiveVO(){}
	
	public ReceiveVO(String orderID, String transitID, String data,
			String depature, StateEnum state) {
		super();
		this.orderID = orderID;
		this.transitID = transitID;
		this.data = data;
		this.depature = depature;
		this.state = state;
	}
	
	private String orderID;
	private String transitID;
	private String data;
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
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
