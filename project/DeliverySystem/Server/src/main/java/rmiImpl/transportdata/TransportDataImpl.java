package rmiImpl.transportdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import message.OperationMessage;
import po.orderdata.OrderPO;
import po.transportdata.CenterOutPO;
import po.transportdata.LoadPO;
import po.transportdata.TransportPO;
import rmi.transportdata.TransportDataService;
import rmiImpl.CommonData;

public class TransportDataImpl extends CommonData<TransportPO> implements TransportDataService{

	public TransportDataImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
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

	@Override
	public TransportPO getFormPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TransportPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
