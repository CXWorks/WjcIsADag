package rmiImpl.receivedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import message.OperationMessage;
import po.receivedata.ReceivePO;
import rmi.receivedata.ReceiveDataService;
import rmiImpl.CommonData;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class ReceiveDataImpl extends CommonData<ReceivePO> implements ReceiveDataService {

	public ReceiveDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public OperationMessage insert(ReceivePO po) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage delete(String id) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage update(ReceivePO po) {
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

	public ReceivePO getFormPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return new ReceivePO();
	}

	public ArrayList<ReceivePO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<ReceivePO> result=new ArrayList<ReceivePO>();
		ReceivePO stub=new ReceivePO();
		result.add(stub);
		return result;
	}

}
