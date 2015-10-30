package bl.blImpl.receivebl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import message.CheckFormMessage;
import message.OperationMessage;
import po.receivedata.ReceivePO;
import vo.FormVO;
import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;
import vo.receivevo.ReceiveVO;
import vo.transitvo.CenterOutVO;
import vo.transitvo.LoadVO;
import vo.transitvo.TransitVO;
import bl.blService.receiveblService.ReceiveBLService;

/** 
 * Client//blImpl.receivebl//ReceiveblImpl.java
 * @author wjc
 * @date 2015年10月25日 
 * @version 1.0 
 */
public class ReceiveblImpl implements ReceiveBLService {

	public ArrayList<CheckFormMessage> checkFormat(ReceiveVO form,
			boolean isFinal) {
		// TODO Auto-generated method stub
		ArrayList<CheckFormMessage> result =new ArrayList<CheckFormMessage>();
		CheckFormMessage stub=new CheckFormMessage();
		result.add(stub);
		return result;
	}

	public OperationMessage submit(ReceiveVO form) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage saveDraft(ReceiveVO form) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public ReceiveVO loadDraft() {
		// TODO Auto-generated method stub
		return new ReceiveVO();
	}

	public OrderVO getOrderVO(String orderID) {
		// TODO Auto-generated method stub
		return new OrderVO();
	}

	public TransitVO getTransitVO() {
		// TODO Auto-generated method stub
		return new CenterOutVO();
//		return new LoadVO();
	}


}
