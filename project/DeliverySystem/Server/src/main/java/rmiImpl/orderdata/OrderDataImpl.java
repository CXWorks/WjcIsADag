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
import rmiImpl.CommonData;

/**
 * 
 * @author mx
 *2015/10/25
 */

public class OrderDataImpl extends CommonData<OrderPO> implements OrderDataService {

	public OrderDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public OperationMessage insert(OrderPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage delete(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage update(OrderPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage clear() throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public String newID() throws RemoteException {
		// TODO Auto-generated method stub
		return "1010101010";
	}

	@Override
	public OrderPO getFormPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<OrderPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}



	
}
