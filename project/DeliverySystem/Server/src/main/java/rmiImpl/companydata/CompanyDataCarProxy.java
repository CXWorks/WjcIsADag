package rmiImpl.companydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.CarPO;
import po.systemdata.SystemState;
import rmi.companydata.CompanyDataCarService;
import rmiImpl.initaldata.InitialDataProxy;

public class CompanyDataCarProxy extends UnicastRemoteObject implements CompanyDataCarService {

	CompanyDataCarService companyDataCarService = new CompanyDataCarImpl();

	public CompanyDataCarProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CarPO> getCars(String unitID) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCarService.getCars(unitID);
		return null;
	}

	@Override
	public CarPO getCar(String carID) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCarService.getCar(carID);
		return null;
	}

	@Override
	public String newCarID(String unitID) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCarService.newCarID(unitID);
		return null;
	}

	@Override
	public OperationMessage addCar(CarPO car) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCarService.addCar(car);
		return null;
	}

	@Override
	public OperationMessage deleteCar(CarPO car) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCarService.deleteCar(car);
		return null;
	}

	@Override
	public OperationMessage modifyCar(CarPO car) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCarService.modifyCar(car);
		return null;
	}

	@Override
	public ArrayList<CarPO> availableCar(String unitID) throws RemoteException {
		// TODO Auto-generated method stub
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCarService.availableCar(unitID);
		return null;
	}

}
