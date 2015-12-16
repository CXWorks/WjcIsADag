package rmiImpl.companydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.CenterPO;
import po.systemdata.SystemState;
import rmi.companydata.CompanyDataCenterService;
import rmiImpl.initaldata.InitialDataProxy;

public class CompanyDataCenterProxy extends UnicastRemoteObject implements CompanyDataCenterService {

	CompanyDataCenterService companyDataCenterService = new CompanyDataCenterImpl();

	public CompanyDataCenterProxy() throws RemoteException {
		super();
	}

	@Override
	public Connection getConn() throws RemoteException {
		return null;
	}

	@Override
	public CenterPO getCenterByID(String ID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCenterService.getCenterByID(ID);
		return null;
	}

	@Override
	public ArrayList<CenterPO> getCenter() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCenterService.getCenter();
		return null;
	}

	@Override
	public OperationMessage addCenter(CenterPO center) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCenterService.addCenter(center);
		return null;
	}

	@Override
	public OperationMessage deleteCenter(CenterPO center) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCenterService.deleteCenter(center);
		return null;
	}

	@Override
	public OperationMessage modifyCenter(CenterPO center) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCenterService.modifyCenter(center);
		return null;
	}

	@Override
	public String newCenterID(String city) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCenterService.newCenterID(city);
		return null;
	}

}
