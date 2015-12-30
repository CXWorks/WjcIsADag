package rmiImpl.companydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import operation.Operation;
import operation.OperationTypeEnum;
import cache.CacheLogService;
import database.RMIHelper;
import message.OperationMessage;
import po.companydata.CenterPO;
import po.systemdata.LogPO;
import po.systemdata.SystemState;
import rmi.cachedata.CacheDataService;
import rmi.companydata.CompanyDataCenterService;
import rmiImpl.Logger;
import rmiImpl.initaldata.InitialDataProxy;

public class CompanyDataCenterProxy extends UnicastRemoteObject implements CompanyDataCenterService {

	CompanyDataCenterService companyDataCenterService = new CompanyDataCenterImpl();
	CacheLogService cacheLogService;
	CacheDataService cacheDataService;

	public CompanyDataCenterProxy() throws RemoteException {
		super();
		Logger logger=new CompanyDataCenterLogger();
		this.cacheDataService=logger;
		this.cacheLogService=logger;
	}

	@Override
	public CenterPO getCenterByID(String ID) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCenterService.getCenterByID(ID);
		return null;
	}

	@Override
	public ArrayList<CenterPO> getCenter() throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCenterService.getCenter();
		return null;
	}

	@Override
	public OperationMessage addCenter(CenterPO center) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			OperationMessage message = companyDataCenterService.addCenter(center);
			// 系统日志
			if (message.operationResult == true){
				RMIHelper.getLogDataService()
						.insert(new LogPO("总经理", Calendar.getInstance(), "新建中转中心:" + center.getCenterID()));
			cacheLogService.addNewOperation(Operation.build(OperationTypeEnum.NEW, center));
			}
			return message;
		}
		return null;
	}

	@Override
	public OperationMessage deleteCenter(CenterPO center) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)){
			OperationMessage message = companyDataCenterService.deleteCenter(center);
			// 系统日志
			if (message.operationResult == true){
				RMIHelper.getLogDataService()
						.insert(new LogPO("总经理", Calendar.getInstance(), "删除中转中心:" + center.getCenterID()));
				cacheLogService.addNewOperation(Operation.build(OperationTypeEnum.DELETE, center));
			}
			return message;
		}
		return null;
	}

	@Override
	public OperationMessage modifyCenter(CenterPO center) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)){
			OperationMessage message = companyDataCenterService.modifyCenter(center);
			if (message.operationResult) {
				cacheLogService.addNewOperation(Operation.build(OperationTypeEnum.MODIFY, center));
			}
		}
		return null;
	}

	@Override
	public String newCenterID(String city) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return companyDataCenterService.newCenterID(city);
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
