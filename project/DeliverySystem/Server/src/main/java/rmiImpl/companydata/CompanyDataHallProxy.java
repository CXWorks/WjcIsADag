package rmiImpl.companydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;

import database.RMIHelper;
import message.OperationMessage;
import po.companydata.HallPO;
import po.systemdata.LogPO;
import po.systemdata.SystemState;
import rmi.companydata.CompanyDataHallService;
import rmiImpl.initaldata.InitialDataProxy;

public class CompanyDataHallProxy extends UnicastRemoteObject implements CompanyDataHallService {

	CompanyDataHallService companyDataHallService = new CompanyDataHallImpl();

	public CompanyDataHallProxy() throws RemoteException {
		super();
	}

	@Override
	public Connection getConn() throws RemoteException {
		return null;
	}

	@Override
	public HallPO getHallByID(String ID) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataHallService.getHallByID(ID);
		return null;
	}

	@Override
	public ArrayList<HallPO> getHall() throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataHallService.getHall();
		return null;
	}

	@Override
	public OperationMessage addHall(HallPO hall) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			OperationMessage message = companyDataHallService.addHall(hall);
			// 系统日志
			if (message.operationResult == true)
				RMIHelper.getLogDataService()
						.insert(new LogPO("总经理", Calendar.getInstance(), "新建营业厅:" + hall.getHallID()));

			return message;
		}
		return null;
	}

	@Override
	public OperationMessage deleteHall(HallPO hall) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			OperationMessage message = companyDataHallService.deleteHall(hall);
			// 系统日志
			if (message.operationResult == true)
				RMIHelper.getLogDataService()
						.insert(new LogPO("总经理", Calendar.getInstance(), "删除营业厅:" + hall.getHallID()));

			return message;
		}
		return null;
	}

	@Override
	public OperationMessage modifyHall(HallPO hall) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataHallService.modifyHall(hall);
		return null;
	}

	@Override
	public String newHallID(String city) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataHallService.newHallID(city);
		return null;
	}

}
