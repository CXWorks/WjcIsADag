package rmiImpl.companydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cache.CacheLogService;
import operation.Operation;
import operation.OperationTypeEnum;
import database.RMIHelper;
import message.OperationMessage;
import po.companydata.HallPO;
import po.systemdata.LogPO;
import po.systemdata.SystemState;
import rmi.cachedata.CacheDataService;
import rmi.companydata.CompanyDataHallService;
import rmiImpl.Logger;
import rmiImpl.initaldata.InitialDataProxy;

public class CompanyDataHallProxy extends UnicastRemoteObject implements CompanyDataHallService {

	CompanyDataHallService companyDataHallService = new CompanyDataHallImpl();
	CacheLogService cacheLogService;
	CacheDataService cacheDataService;


	public CompanyDataHallProxy() throws RemoteException {
		super();
		Logger logger=new CompanyDataHallLogger();
		this.cacheDataService=logger;
		this.cacheLogService=logger;
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
			if (message.operationResult == true){
				RMIHelper.getLogDataService()
						.insert(new LogPO("总经理", Calendar.getInstance(), "新建营业厅:" + hall.getHallID()));
				cacheLogService.addNewOperation(Operation.build(OperationTypeEnum.NEW, hall));
			}
			return message;
		}
		return null;
	}

	@Override
	public OperationMessage deleteHall(HallPO hall) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			OperationMessage message = companyDataHallService.deleteHall(hall);
			// 系统日志
			if (message.operationResult == true){
				RMIHelper.getLogDataService()
						.insert(new LogPO("总经理", Calendar.getInstance(), "删除营业厅:" + hall.getHallID()));
				cacheLogService.addNewOperation(Operation.build(OperationTypeEnum.DELETE, hall));
			}
			return message;
		}
		return null;
	}

	@Override
	public OperationMessage modifyHall(HallPO hall) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)){
			OperationMessage message = companyDataHallService.modifyHall(hall);
			if (message.operationResult) {
				cacheLogService.addNewOperation(Operation.build(OperationTypeEnum.MODIFY, hall));
			}
			return message;
		}
		return null;
	}

	@Override
	public String newHallID(String city) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataHallService.newHallID(city);
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.cachedata.CacheDataService#getLatestVersionID()
	 */
	@Override
	public long getLatestVersionID() throws RemoteException {
		return cacheDataService.getLatestVersionID();
	}

	/* (non-Javadoc)
	 * @see rmi.cachedata.CacheDataService#getOperation(long)
	 */
	@Override
	public List<Operation> getOperation(long localVersion)
			throws RemoteException {
		return cacheDataService.getOperation(localVersion);
	}

}
