package blImpl.deliverbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import message.CheckFormMessage;
import message.OperationMessage;
import po.deliverdata.DeliverPO;
import po.receivedata.ReceivePO;
import vo.FormVO;
import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;
import blService.deliverblService.DeliverBLService;


/** 
 * Client//blImpl.deliverbl//DeliverblImpl.java
 * @author wjc
 * @date 2015年10月25日 
 * @version 1.0 
 */
public class DeliverblImpl implements DeliverBLService {

	public OperationMessage saveDraft(FormVO form) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList checkFormat(FormVO form, boolean isFinal) {
		// TODO Auto-generated method stub
		return null;
	}

	public OperationMessage submit(FormVO form) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<CheckFormMessage> checkFormat(DeliverVO form,
			boolean isFinal) {
		// TODO Auto-generated method stub
		return null;
	}

	public OperationMessage submit(DeliverVO form) {
		// TODO Auto-generated method stub
		return null;
	}

	public OperationMessage saveDraft(DeliverVO form) {
		// TODO Auto-generated method stub
		return null;
	}

	public DeliverVO loadDraft() {
		// TODO Auto-generated method stub
		return null;
	}

	public OrderVO getOrderVO(String orderID) {
		// TODO Auto-generated method stub
		return null;
	}

}
