package rmiImpl.transportdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import message.OperationMessage;
import po.transportdata.CenterOutPO;
import po.transportdata.LoadPO;
import po.transportdata.TransportPO;
import rmi.transportdata.TransportDataService;

public class TransportDataImpl extends UnicastRemoteObject implements TransportDataService{

	public TransportDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

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
