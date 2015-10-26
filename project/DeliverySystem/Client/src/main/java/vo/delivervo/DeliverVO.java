package vo.delivervo;

import vo.FormVO;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class DeliverVO extends FormVO{
	
	private String orderID;
	private String data;
	private String postman;
	
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getPostman() {
		return postman;
	}
	public void setPostman(String postman) {
		this.postman = postman;
	}

}
