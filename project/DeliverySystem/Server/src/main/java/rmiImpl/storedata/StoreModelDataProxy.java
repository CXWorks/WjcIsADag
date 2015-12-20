package rmiImpl.storedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import database.RMIHelper;
import message.OperationMessage;
import model.store.StoreArea;
import model.store.StoreAreaCode;
import model.store.StoreLocation;
import model.store.StoreModel;
import po.systemdata.LogPO;
import po.systemdata.SystemState;
import rmi.storedata.StoreModelDataService;
import rmiImpl.initaldata.InitialDataProxy;

public class StoreModelDataProxy extends UnicastRemoteObject implements StoreModelDataService {

	StoreModelDataService storeModelDataService = new StoreModelDataImpl();

	public StoreModelDataProxy() throws RemoteException {
		super();
	}

	@Override
	public Connection getConn() throws RemoteException {
		return null;
	}

	@Override
	public OperationMessage setLocation(String centerID, StoreLocation location) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeModelDataService.setLocation(centerID, location);
		return null;
	}

	@Override
	public StoreArea getArea(String centerID, StoreAreaCode code) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeModelDataService.getArea(centerID, code);
		return null;
	}

	@Override
	public StoreModel getModel(String centerID) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeModelDataService.getModel(centerID);
		return null;
	}

	@Override
	public OperationMessage newShelf(String centerID, StoreAreaCode code, int row, int shelf) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			OperationMessage message = storeModelDataService.newShelf(centerID, code, row, shelf);
			// 系统日志
			if (message.operationResult == true)
				RMIHelper.getLogDataService().insert(new LogPO("仓库管理人员", Calendar.getInstance(), "新增货架"));

			return message;
		}
		return null;
	}

	@Override
	public OperationMessage removeShelf(String centerID, StoreAreaCode code, int row, int shelf)
			throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL)) {
			OperationMessage message = storeModelDataService.removeShelf(centerID, code, row, shelf);
			// 系统日志
			if (message.operationResult == true)
				RMIHelper.getLogDataService().insert(new LogPO("仓库管理人员", Calendar.getInstance(), "移除货架"));

			return message;
		}
		return null;
	}

	@Override
	public OperationMessage moveShelf(String centerID, StoreAreaCode code_now, int row_now, int shelf_now,
			StoreAreaCode code, int row, int shelf) throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeModelDataService.moveShelf(centerID, code_now, row_now, shelf_now, code, row, shelf);
		return null;
	}

	@Override
	public String getLocation(String centerID, StoreAreaCode code, int row, int shelf, int position)
			throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeModelDataService.getLocation(centerID, code, row, shelf, position);
		return null;
	}

	@Override
	public List<StoreModel> getModels() throws RemoteException {
		if (InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeModelDataService.getModels();
		return null;
	}

}
