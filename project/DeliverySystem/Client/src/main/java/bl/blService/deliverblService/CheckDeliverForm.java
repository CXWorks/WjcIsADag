package bl.blService.deliverblService;

import java.util.ArrayList;

import message.OperationMessage;
import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;

/** 
 * Client//bl.blService.deliverblService//CheckDeliverForm.java
 * @author CXWorks
 * @date 2015年11月15日 下午3:14:53
 * @version 1.0 
 */
public interface CheckDeliverForm {
	/**
	 * 快递员查看自己的派件单
	 * @param postmanID
	 * @return
	 */
	public ArrayList<DeliverVO> getDeliverForms(String postmanID);
	
	public OperationMessage finishDelivery(DeliverVO finfished);
	
	public ArrayList<OrderVO> getDeliverOrder(String postmanID);
}
