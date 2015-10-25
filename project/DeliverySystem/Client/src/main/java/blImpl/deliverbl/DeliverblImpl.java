package blImpl.deliverbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import message.CheckFormMessage;
import message.OperationMessage;
import po.deliverdata.DeliverPO;
import po.receivedata.ReceivePO;
import vo.ordervo.OrderVO;
import blService.deliverblService.DeliverBLService;


/** 
 * Client//blImpl.deliverbl//DeliverblImpl.java
 * @author wjc
 * @date 2015年10月25日 
 * @version 1.0 
 */
public class DeliverblImpl extends UnicastRemoteObject implements DeliverBLService {

	protected DeliverblImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<CheckFormMessage> checkFormat(DeliverPO form) {
		// TODO Auto-generated method stub
		ArrayList<CheckFormMessage> result =new ArrayList<CheckFormMessage>();
		CheckFormMessage stub=new CheckFormMessage();
		result.add(stub);
		return result;
	}

	public OperationMessage submit(DeliverPO form) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage saveDraft() {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public DeliverPO loadDraft() {
		// TODO Auto-generated method stub
		return new DeliverPO();
	}

	public OrderVO getOrderVO(String orderID) {
		// TODO Auto-generated method stub
		return new OrderVO();
	}

}
