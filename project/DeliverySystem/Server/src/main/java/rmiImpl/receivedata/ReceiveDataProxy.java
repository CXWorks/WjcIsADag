package rmiImpl.receivedata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
import po.receivedata.ReceivePO;
import rmi.receivedata.ReceiveDataService;
import rmiImpl.CommonData;
import rmiImpl.initaldata.InitialDataProxy;

public class ReceiveDataProxy extends CommonData<ReceivePO> implements ReceiveDataService {

	ReceiveDataService receiveDataService = new ReceiveDataImpl();

	public ReceiveDataProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public OperationMessage insert(ReceivePO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return receiveDataService.insert(po);
		return null;
	}

	@Override
	public ReceivePO getFormPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return receiveDataService.getFormPO(id);
		return null;
	}

	@Override
	public OperationMessage delete(String id) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return receiveDataService.delete(id);
		return null;
	}

	@Override
	public OperationMessage update(ReceivePO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return receiveDataService.update(po);
		return null;
	}

	@Override
	public String newID(String unitID) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return receiveDataService.newID(unitID);
		return null;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return receiveDataService.clear();
		return null;
	}

	@Override
	public ArrayList<ReceivePO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return receiveDataService.getAll();
		return null;
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
