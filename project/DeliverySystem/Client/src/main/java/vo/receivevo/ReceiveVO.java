package vo.receivevo;

import java.util.Calendar;

import po.FormEnum;
import po.FormStateEnum;
import po.receivedata.ReceivePO;
import po.receivedata.StateEnum;
import vo.FormVO;

/**
 *
 * @author wjc
 *2015/10/24
 */

public class ReceiveVO extends FormVO{
	public ReceivePO toPO(){
		return new ReceivePO(formID,orderID, transitID, (Calendar)date.clone(), depature, orderState.toString(),createrID);
	}
	public ReceiveVO(String formID,String createrID){
		super(FormEnum.RECEIVE,FormStateEnum.CONSTRUCTED,formID,createrID);
	}
	//
	public ReceiveVO(ReceivePO po){
		this(po.getFormID(),po.getOrderID(), po.getTransitID(), po.getDate(),po.getDepature(), po.getState(),po.getCreatorID());
	}
	public ReceiveVO(String formID,String orderID, String transitID, Calendar date,
			String depature, StateEnum state,String createrID) {
		this(formID,createrID);
		this.orderID = orderID;
		this.transitID = transitID;
		this.date = date;
		this.depature = depature;
		this.orderState = state;
	}

	private String orderID;
	private String transitID;
	private Calendar date;
	private String depature;
	private StateEnum orderState;


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

	public StateEnum getOrderState() {
		return orderState;
	}
	public void setState(StateEnum state) {
		this.orderState = state;
	}
	/* (non-Javadoc)
	 * @see vo.FormVO#getMainInfo()
	 */
	@Override
	public String getMainInfo() {
		return depature;
	}

}
