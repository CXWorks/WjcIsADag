package vo.delivervo;

import java.util.Calendar;

import po.FormEnum;
import po.FormStateEnum;
import po.deliverdata.DeliverPO;
import vo.FormVO;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class DeliverVO extends FormVO{
	
	private String orderID;
	private Calendar date;
	private String postman;
	private boolean finished;
	
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public String getPostman() {
		return postman;
	}
	public void setPostman(String postman) {
		this.postman = postman;
	}
	public DeliverVO(String formID){
		super(FormEnum.DELIVER,FormStateEnum.CONSTRUCTED,formID);
		this.finished=false;
	}
	//
	/**
	 * @param orderID
	 * @param date
	 * @param postman
	 */
	public DeliverVO(String formID,String orderID, Calendar date, String postman) {
		this(formID);
		this.orderID = orderID;
		this.date = date;
		this.postman = postman;
	}
	public DeliverVO(DeliverPO po){
		this(po.getFormID(),po.getOrderID(), (Calendar)po.getDate().clone(), po.getPostman());
		this.finished=po.isFinished();
	}
	public DeliverPO toPO(){
		DeliverPO po= new DeliverPO(formID, orderID, date, postman);
		po.setFinished(finished);
		return po;
	}
}
