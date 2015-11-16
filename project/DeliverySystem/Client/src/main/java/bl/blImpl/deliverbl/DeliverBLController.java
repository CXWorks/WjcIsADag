package bl.blImpl.deliverbl;

import java.util.ArrayList;

import message.CheckFormMessage;
import message.OperationMessage;
import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;
import bl.blService.deliverblService.DeliverBLService;

/** 
 * Client//bl.blImpl.deliverbl//DeliverBLController.java
 * @author CXWorks
 * @date 2015年11月15日 下午4:45:57
 * @version 1.0 
 */
public class DeliverBLController implements DeliverBLService {
	Deliver deliver;
	/* (non-Javadoc)
	 * @see bl.blService.FormBLService#newID()
	 */
	public String newID() {
		// TODO Auto-generated method stub
		return "2333";
	}

	/* (non-Javadoc)
	 * @see bl.blService.deliverblService.DeliverBLService#checkFormat(vo.delivervo.DeliverVO, boolean)
	 */
	public ArrayList<CheckFormMessage> checkFormat(DeliverVO form,
			boolean isFinal) {
		// TODO Auto-generated method stub
		return new ArrayList<CheckFormMessage>();
	}

	/* (non-Javadoc)
	 * @see bl.blService.deliverblService.DeliverBLService#submit(vo.delivervo.DeliverVO)
	 */
	public OperationMessage submit(DeliverVO form) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see bl.blService.deliverblService.DeliverBLService#saveDraft(vo.delivervo.DeliverVO)
	 */
	public OperationMessage saveDraft(DeliverVO form) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see bl.blService.deliverblService.DeliverBLService#loadDraft()
	 */
	public DeliverVO loadDraft() {
		// TODO Auto-generated method stub
		return new DeliverVO();
	}

	/* (non-Javadoc)
	 * @see bl.blService.deliverblService.DeliverBLService#getOrderVO(java.lang.String)
	 */
	public OrderVO getOrderVO(String orderID) {
		// TODO Auto-generated method stub
		return new OrderVO();
	}

}
