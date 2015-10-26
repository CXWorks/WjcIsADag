package rmiImpl.deliverdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import message.OperationMessage;
import po.deliverdata.DeliverPO;
import rmi.deliverdata.DeliverDataService;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class DeliverDataImpl extends UnicastRemoteObject implements DeliverDataService {

	public DeliverDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public OperationMessage insert(DeliverPO po) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see rmi.deliverdata.DeliverDataService#getDeliverPO(java.lang.String)
	 */
	public DeliverPO getDeliverPO(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public OperationMessage delete(String id) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage update(DeliverPO po) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage init(String id) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public String newID() {
		// TODO Auto-generated method stub
		return "1000101";
	}

	public OperationMessage clear() {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public ArrayList<DeliverPO> show() {
		// TODO Auto-generated method stub
		ArrayList<DeliverPO> result =new ArrayList<DeliverPO>();
		DeliverPO stub=new DeliverPO();
		result.add(stub);
		return result;
	}
}
