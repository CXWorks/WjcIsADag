package rmiImpl.transportdata;

import message.OperationMessage;
import po.transportdata.CenterPO;
import po.transportdata.LoadPO;
import po.transportdata.TransportPO;
import rmi.transportdata.TransportDataService;

public class TransportDataImpl implements TransportDataService{

	public TransportPO getTransportPO(String id) {
		// TODO Auto-generated method stub
		return new LoadPO();
	}

	public OperationMessage insert(TransportPO po) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage delete(String id) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage update(TransportPO po) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage clear() {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public String newID() {
		// TODO Auto-generated method stub
		return "025000201510250000001";
	}

}
