package rmiImpl.transportdata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
import po.transportdata.CenterOutPO;
import rmi.transportdata.CenterOutDataService;
import rmiImpl.CommonData;
import rmiImpl.initaldata.InitialDataProxy;

public class CenterOutDataProxy extends CommonData<CenterOutPO> implements CenterOutDataService {

	CenterOutDataService centerOutDataService = new CenterOutDataImpl();

	public CenterOutDataProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public OperationMessage insert(CenterOutPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if (!InitialDataProxy.isSystem_on_initial())
			return centerOutDataService.insert(po);
		return null;
	}

	@Override
	public CenterOutPO getFormPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		if (!InitialDataProxy.isSystem_on_initial())
			return centerOutDataService.getFormPO(id);
		return null;
	}

	@Override
	public OperationMessage delete(String id) throws RemoteException {
		// TODO Auto-generated method stub
		if (!InitialDataProxy.isSystem_on_initial())
			return centerOutDataService.delete(id);
		return null;
	}

	@Override
	public OperationMessage update(CenterOutPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if (!InitialDataProxy.isSystem_on_initial())
			return centerOutDataService.update(po);
		return null;
	}

	@Override
	public String newID(String unitID) throws RemoteException {
		// TODO Auto-generated method stub
		if (!InitialDataProxy.isSystem_on_initial())
			return centerOutDataService.newID(unitID);
		return null;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
		// TODO Auto-generated method stub
		if (!InitialDataProxy.isSystem_on_initial())
			return centerOutDataService.clear();
		return null;
	}

	@Override
	public ArrayList<CenterOutPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		if (!InitialDataProxy.isSystem_on_initial())
			return centerOutDataService.getAll();
		return null;
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
