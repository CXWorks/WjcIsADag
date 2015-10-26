package rmiImpl.orderdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import message.OperationMessage;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import po.orderdata.OrderPO;
import rmi.memberdata.MemberDataService;
import rmi.orderdata.OrderDataService;

/**
 * 
 * @author mx
 *2015/10/25
 */

public class OrderDataImpl extends UnicastRemoteObject implements OrderDataService {

	public OrderDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderPO getOrderPO(String id) {
		// TODO Auto-generated method stub
		return new OrderPO();
	}

	public OperationMessage insert(OrderPO po) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage delete(String id) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage update(OrderPO po) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage clear() {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public String newID() {
		// TODO Auto-generated method stub
		return "1010101010";
	}



	
}
