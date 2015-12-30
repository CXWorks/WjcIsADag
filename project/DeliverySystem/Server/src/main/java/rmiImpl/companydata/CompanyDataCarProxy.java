package rmiImpl.companydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;

import database.RMIHelper;
import message.OperationMessage;
import po.companydata.CarPO;
import po.systemdata.LogPO;
import po.systemdata.SystemState;
import rmi.companydata.CompanyDataCarService;
import rmiImpl.initaldata.InitialDataProxy;

public class CompanyDataCarProxy extends UnicastRemoteObject implements CompanyDataCarService {

	CompanyDataCarService companyDataCarService = new CompanyDataCarImpl();

	public CompanyDataCarProxy() throws RemoteException {
		super();
	}

	@Override
	public ArrayList<CarPO> getCars(String unitID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCarService.getCars(unitID);
		return null;
	}

	@Override
	public CarPO getCar(String carID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCarService.getCar(carID);
		return null;
	}

	@Override
	public String newCarID(String unitID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCarService.newCarID(unitID);
		return null;
	}

	@Override
	public OperationMessage addCar(CarPO car) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			OperationMessage message = companyDataCarService.addCar(car);
			//系统日志
			if(message.operationResult==true)
				RMIHelper.getLogDataService().insert(new LogPO("业务员", Calendar.getInstance(), "新建车辆:" + car.getCarID()));

			return message;
		}
		return null;
	}

	@Override
	public OperationMessage deleteCar(CarPO car) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			OperationMessage message = companyDataCarService.deleteCar(car);
			//系统日志
			if(message.operationResult==true)
				RMIHelper.getLogDataService().insert(new LogPO("业务员", Calendar.getInstance(), "删除车辆:" + car.getCarID()));

			return message;
		}
		return null;
	}

	@Override
	public OperationMessage modifyCar(CarPO car) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCarService.modifyCar(car);
		return null;
	}

	@Override
	public ArrayList<CarPO> availableCar(String unitID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCarService.availableCar(unitID);
		return null;
	}

}
