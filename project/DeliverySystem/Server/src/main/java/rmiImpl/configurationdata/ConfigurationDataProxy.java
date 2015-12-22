package rmiImpl.configurationdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cache.CacheLogService;
import database.RMIHelper;
import operation.Operation;
import operation.OperationTypeEnum;
import message.OperationMessage;
import po.configurationdata.City2DPO;
import po.configurationdata.PackPO;
import po.configurationdata.PricePO;
import po.configurationdata.ProportionPO;
import po.configurationdata.SalaryStrategyPO;
import po.systemdata.LogPO;
import po.systemdata.SystemState;
import rmi.cachedata.CacheDataService;
import rmi.configurationdata.ConfigurationDataService;
import rmiImpl.initaldata.InitialDataProxy;

public class ConfigurationDataProxy extends UnicastRemoteObject implements ConfigurationDataService {

	ConfigurationDataService configurationDataService = new ConfigurationDataImpl();
	CacheLogService cacheLogService;
	CacheDataService cacheDataService;

	public ConfigurationDataProxy() throws RemoteException {
		super();
		ConfigurationLogger configurationLogger = new ConfigurationLogger();
		this.cacheDataService = configurationLogger;
		this.cacheLogService = configurationLogger;
	}

	@Override
	public Connection getConn() throws RemoteException {
		return null;
	}

	@Override
	public OperationMessage newCity2D(City2DPO po) throws RemoteException {
		OperationMessage res = null;
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			res = configurationDataService.newCity2D(po);
			// 系统日志
			if (res.operationResult == true)
				RMIHelper.getLogDataService().insert(new LogPO("总经理", Calendar.getInstance(), "新增城市信息" + po.getName()));

		}
		if (res != null && res.operationResult) {
			cacheLogService.addNewOperation(Operation.build(OperationTypeEnum.NEW, po));
			return res;
		}
		return new OperationMessage(false, "system is " + InitialDataProxy.getState().getChinese());
	}

	@Override
	public OperationMessage deleteCity2D(String name) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			OperationMessage res = configurationDataService.deleteCity2D(name);
			// 系统日志
			if (res.operationResult == true)
				RMIHelper.getLogDataService().insert(new LogPO("总经理", Calendar.getInstance(), "删除城市信息" + name));
			if (res != null && res.operationResult) {
				cacheLogService.addNewOperation(Operation.build(OperationTypeEnum.DELETE, new City2DPO(name, 0, 0, null)));
				return res;
			}
			return res;
		}
		return null;
	}

	@Override
	public OperationMessage modifyCity2D(City2DPO po) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)){
			OperationMessage res =configurationDataService.modifyCity2D(po);
		
		
			if (res != null && res.operationResult) {
				cacheLogService.addNewOperation(Operation.build(
						OperationTypeEnum.MODIFY, po));
				return res;
			}
		}
		return new OperationMessage(false, "system is "+InitialDataProxy.getState().getChinese());
	}

	@Override
	public City2DPO getCity2D(String name) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.getCity2D(name);
		return null;
	}

	@Override
	public ArrayList<City2DPO> getAllCity2D() throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.getAllCity2D();
		return null;
	}

	@Override
	public OperationMessage clearCity2D() throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.clearCity2D();
		return null;
	}

	@Override
	public ArrayList<SalaryStrategyPO> getSalaryStrategy() throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.getSalaryStrategy();
		return null;
	}

	@Override
	public OperationMessage modifySalaryStrategy(SalaryStrategyPO salaryStrategy) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)){
			OperationMessage res = configurationDataService.modifySalaryStrategy(salaryStrategy);
			if (res != null && res.operationResult) {
				cacheLogService.addNewOperation(Operation.build(OperationTypeEnum.MODIFY, salaryStrategy));
				return res;
			}
		}
		return new OperationMessage(false, "system is "+InitialDataProxy.getState().getChinese());
	}

	@Override
	public PackPO getPack() throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.getPack();
		return null;
	}

	@Override
	public OperationMessage modifyPack(PackPO pack) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			OperationMessage res = configurationDataService.modifyPack(pack);
			// 系统日志
			if (res.operationResult == true)
				RMIHelper.getLogDataService().insert(new LogPO("总经理", Calendar.getInstance(), "修改包装价格"));
			if (res != null && res.operationResult) {
				cacheLogService.addNewOperation(Operation.build(OperationTypeEnum.MODIFY, pack));
				return res;
			}
			return res;
		}
		return null;
	}

	@Override
	public PricePO getPrice() throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.getPrice();
		return null;
	}

	@Override
	public OperationMessage modifyPrice(PricePO price) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			OperationMessage res = configurationDataService.modifyPrice(price);
			// 系统日志
			if (res.operationResult == true)
				RMIHelper.getLogDataService().insert(new LogPO("总经理", Calendar.getInstance(), "修改运费价格"));
			if (res != null && res.operationResult) {
				cacheLogService.addNewOperation(Operation.build(OperationTypeEnum.MODIFY, price));
				return res;
			}
			return res;
		}
		return null;
	}

	@Override
	public ProportionPO getProportion() throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.getProportion();
		return null;
	}

	@Override
	public OperationMessage modifyProportion(ProportionPO proportion) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			OperationMessage res = configurationDataService.modifyProportion(proportion);
			// 系统日志
			if (res.operationResult == true)
				RMIHelper.getLogDataService().insert(new LogPO("总经理", Calendar.getInstance(), "修改运费比例"));
			if (res != null && res.operationResult) {
				cacheLogService.addNewOperation(Operation.build(OperationTypeEnum.MODIFY, proportion));
				return res;
			}
			return res;
		}
		return null;
	}

	@Override
	public OperationMessage newSalaryStrategy(List<SalaryStrategyPO> po) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			OperationMessage res = configurationDataService.newSalaryStrategy(po);
			// 系统日志
			if (res.operationResult == true)
				RMIHelper.getLogDataService().insert(new LogPO("总经理", Calendar.getInstance(), "修改薪水策略"));
			if (res != null && res.operationResult) {
				cacheLogService.addNewOperation(Operation.build(OperationTypeEnum.NEW, po.get(0)));
				return res;
			}
			return res;
		}
		return null;
	}

	@Override
	public OperationMessage newPack(PackPO po) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			OperationMessage res = configurationDataService.newPack(po);
			// 系统日志
			if (res.operationResult == true)
				RMIHelper.getLogDataService().insert(new LogPO("总经理", Calendar.getInstance(), "修改包装价格"));
			if (res != null && res.operationResult) {
				cacheLogService.addNewOperation(Operation.build(OperationTypeEnum.NEW, po));
				return res;
			}
			return res;
		}
		return null;
	}

	@Override
	public OperationMessage newPrice(PricePO po) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			OperationMessage res = configurationDataService.newPrice(po);
			// 系统日志
			if (res.operationResult == true)
				RMIHelper.getLogDataService().insert(new LogPO("总经理", Calendar.getInstance(), "修改运费价格"));
			if (res != null && res.operationResult) {
				cacheLogService.addNewOperation(Operation.build(OperationTypeEnum.NEW, po));
				return res;
			}
			return res;
		}
		return null;
	}

	@Override
	public OperationMessage newProportion(ProportionPO po) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			OperationMessage res = configurationDataService.newProportion(po);
			// 系统日志
			if (res.operationResult == true)
				RMIHelper.getLogDataService().insert(new LogPO("总经理", Calendar.getInstance(), "修改运费比例"));
			if (res != null && res.operationResult) {
				cacheLogService.addNewOperation(Operation.build(OperationTypeEnum.NEW, po));
				return res;
			}
			return res;
		}
		return null;
	}

	@Override
	public double getWarningline(String centerID) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return configurationDataService.getWarningline(centerID);
		return 0;
	}

	@Override
	public OperationMessage setWarningline(String centerID, double value) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			OperationMessage res = configurationDataService.setWarningline(centerID, value);
			// 系统日志
			if (res.operationResult == true)
				RMIHelper.getLogDataService().insert(new LogPO("库存管理人员", Calendar.getInstance(), "修改库存警戒比例"));
			return res;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see rmi.cachedata.CacheDataService#getLatestVersionID()
	 */
	@Override
	public long getLatestVersionID() throws RemoteException {
		return cacheDataService.getLatestVersionID();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see rmi.cachedata.CacheDataService#getOperation(long)
	 */
	@Override
	public List<Operation> getOperation(long localVersion) throws RemoteException {
		return cacheDataService.getOperation(localVersion);
	}

}
