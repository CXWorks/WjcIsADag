package vo.delivervo;

import java.util.Calendar;

import po.FormEnum;
import po.FormStateEnum;
import po.deliverdata.DeliverPO;
import userinfo.UserInfo;
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
	private String receivePeople;
	private Calendar receiveDate;
	public void finfished(Calendar receiveCalendar,String signPeople){
		this.receiveDate=receiveCalendar;
		this.receivePeople=signPeople;
		this.finished=true;
	}


	public String getReceivePeople() {
		return receivePeople;
	}


	public Calendar getReceiveDate() {
		return receiveDate;
	}


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
	public DeliverVO(String formID,String createrID){
		super(FormEnum.DELIVER,FormStateEnum.CONSTRUCTED,formID,createrID);
		this.finished=false;
	}
	//
	/**
	 * @param orderID
	 * @param date
	 * @param postman
	 */
	public DeliverVO(String formID,String orderID, Calendar date, String postman,String createrID) {
		this(formID,createrID);
		this.orderID = orderID;
		this.date = date;
		this.postman = postman;
	}
	public DeliverVO(DeliverPO po){
		this(po.getFormID(),po.getOrderID(), (Calendar)po.getDate().clone(), po.getPostman(),po.getCreaterID());
		if(po.isFinished()==1)
			this.finished=true;
		else
			this.finished=false;
		this.receiveDate=po.getReceiveDate();
		this.receivePeople=po.getReceivePeople();
	}
	public DeliverPO toPO(){
		DeliverPO po= new DeliverPO(formID, orderID, date, postman,createrID);
		po.setFinished(finished);
		if (finished) {
			po.finfished(receiveDate, receivePeople);
		}
		po.setCache_OperatorID(UserInfo.getUserID());
		return po;
	}
	//

	/* (non-Javadoc)
	 * @see vo.FormVO#getMainInfo()
	 */
	@Override
	public String getMainInfo() {
		return "配送员"+this.postman;
	}
}
