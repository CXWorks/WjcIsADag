package rmi.storedata;

import message.OperationMessage;
import model.store.StoreArea;
import model.store.StoreAreaCode;
import model.store.StoreModel;
import model.store.StoreModelOperation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import po.CommonPO;
import rmi.DataService;

/**
 * Created by Sissel on 2015/10/23.
 */
public interface StoreModelDataService extends DataService<CommonPO> {

	// public OperationMessage uploadModelOperations
	// (String centerID, String staffID, List<StoreModelOperation> operations)
	// throws RemoteException;
	//
	// public LinkedList<StoreModelOperation> updateModelOperations
	// (String centerID, String staffID) throws RemoteException;
	//
	// public StoreModel downloadStoreModel (String centerID) throws
	// RemoteException;

	public void setTableName(StoreAreaCode code) throws RemoteException;

	public StoreArea getArea(StoreAreaCode code) throws RemoteException;

	public StoreModel getModel() throws RemoteException;

	public OperationMessage newShelf(StoreAreaCode code, int row)
			throws RemoteException;
	/**
	 * 插入新的信息
	 * @param code_now 要转移的货架所在区域
	 * @param row_now 要转移的货架所在row的编号
	 * @param shelf_now 要转移的货架的编号
	 * @param code 目标区域
	 * @param row 目标row编号
	 * @return 返回操作结果
	 */
	public OperationMessage moveShelf(StoreAreaCode code_now, int row_now,
			int shelf_now, StoreAreaCode code, int row) throws RemoteException;
	
	public OperationMessage checkStoreArea(StoreAreaCode code) throws RemoteException;
}
