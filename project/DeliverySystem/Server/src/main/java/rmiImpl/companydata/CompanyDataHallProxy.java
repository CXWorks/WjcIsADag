package rmiImpl.companydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.HallPO;
import rmi.companydata.CompanyDataHallService;
import rmiImpl.initaldata.InitialDataProxy;

public class CompanyDataHallProxy extends UnicastRemoteObject implements CompanyDataHallService {

	CompanyDataHallService companyDataHallService = new CompanyDataHallImpl();

	public CompanyDataHallProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HallPO getHallByID(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return companyDataHallService.getHallByID(ID);
		return null;
	}

	@Override
	public ArrayList<HallPO> getHall() throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return companyDataHallService.getHall();
		return null;
	}

	@Override
	public OperationMessage addHall(HallPO hall) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return companyDataHallService.addHall(hall);
		return null;
	}

	@Override
	public OperationMessage deleteHall(HallPO hall) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return companyDataHallService.deleteHall(hall);
		return null;
	}

	@Override
	public OperationMessage modifyHall(HallPO hall) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return companyDataHallService.modifyHall(hall);
		return null;
	}

	@Override
	public String newHallID(String city) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return companyDataHallService.newHallID(city);
		return null;
	}

}
