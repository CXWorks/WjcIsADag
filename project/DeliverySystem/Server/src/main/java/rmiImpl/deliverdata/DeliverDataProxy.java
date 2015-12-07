package rmiImpl.deliverdata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
import po.deliverdata.DeliverPO;
import rmi.deliverdata.DeliverDataService;
import rmiImpl.CommonData;
import rmiImpl.initaldata.InitialDataProxy;

public class DeliverDataProxy extends CommonData<DeliverPO> implements DeliverDataService {

	DeliverDataService deliverDataService = new DeliverDataImpl();

	public DeliverDataProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public OperationMessage insert(DeliverPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return deliverDataService.insert(po);
		return null;
	}

	@Override
	public DeliverPO getFormPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return deliverDataService.getFormPO(id);
		return null;
	}

	@Override
	public OperationMessage delete(String id) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return deliverDataService.delete(id);
		return null;
	}

	@Override
	public OperationMessage update(DeliverPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return deliverDataService.update(po);
		return null;
	}

	@Override
	public String newID(String unitID) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return deliverDataService.newID(unitID);
		return null;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return deliverDataService.clear();
		return null;
	}

	@Override
	public ArrayList<DeliverPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return deliverDataService.getAll();
		return null;
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> available(String HallID) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return deliverDataService.available(HallID);
		return null;
	}

	@Override
	public ArrayList<String> searchAsPerson(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return deliverDataService.searchAsPerson(ID);
		return null;
	}

}
