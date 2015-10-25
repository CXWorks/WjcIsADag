package rmiImpl.receivedata;

import java.util.ArrayList;

import message.OperationMessage;
import po.receivedata.ReceivePO;
import rmi.receivedata.ReceiveDataService;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class ReceiveDataImpl implements ReceiveDataService {

	public OperationMessage insert(ReceivePO po) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public ReceivePO find(String id) {
		// TODO Auto-generated method stub
		return new ReceivePO();
	}

	public OperationMessage delete(String id) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage update(ReceivePO po) {
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

	public ArrayList<ReceivePO> show() {
		// TODO Auto-generated method stub
		ArrayList<ReceivePO> result =new ArrayList<ReceivePO>();
		ReceivePO stub=new ReceivePO();
		result.add(stub);
		return result;
	}

}
