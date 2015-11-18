package vo.delivervo;

import java.util.Calendar;

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
	public DeliverVO(){}
	//
	/**
	 * @param orderID
	 * @param date
	 * @param postman
	 */
	public DeliverVO(String orderID, Calendar date, String postman) {
		super();
		this.orderID = orderID;
		this.date = date;
		this.postman = postman;
	}
	public DeliverVO(DeliverPO po){
		this(po.getOrderID(), (Calendar)po.getDate().clone(), po.getPostman());
	}
}
