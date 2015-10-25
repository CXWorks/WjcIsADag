package blImpl.receivebl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import message.CheckFormMessage;
import message.OperationMessage;
import po.receivedata.ReceivePO;
import vo.ordervo.OrderVO;
import vo.transitvo.CenterOutVO;
import vo.transitvo.LoadVO;
import vo.transitvo.TransitVO;
import blService.receiveblService.ReceiveBLService;

/** 
 * Client//blImpl.receivebl//ReceiveblImpl.java
 * @author wjc
 * @date 2015年10月25日 
 * @version 1.0 
 */
public class ReceiveblImpl extends UnicastRemoteObject implements ReceiveBLService {

	protected ReceiveblImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<CheckFormMessage> checkFormat(ReceivePO form) {
		// TODO Auto-generated method stub
		ArrayList<CheckFormMessage> result =new ArrayList<CheckFormMessage>();
		CheckFormMessage stub=new CheckFormMessage();
		result.add(stub);
		return result;
	}

	public OperationMessage submit(ReceivePO form) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage saveDraft() {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public ReceivePO loadDraft() {
		// TODO Auto-generated method stub
		return new ReceivePO();
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
