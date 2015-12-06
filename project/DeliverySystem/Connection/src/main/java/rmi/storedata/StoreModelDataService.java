package rmi.storedata;

import message.OperationMessage;
import model.store.StoreArea;
import model.store.StoreAreaCode;
import model.store.StoreLocation;
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

	// public void setTableName(StoreAreaCode code) throws RemoteException;

	public OperationMessage setLocation(String centerID,StoreLocation location)
			throws RemoteException;

	public StoreArea getArea(String centerID,StoreAreaCode code) throws RemoteException;

	public StoreModel getModel(String centerID) throws RemoteException;

	public OperationMessage newShelf(String centerID,StoreAreaCode code, int row,int shelf)
			throws RemoteException;

	public OperationMessage removeShelf(String centerID,StoreAreaCode code, int row,int shelf)
			throws RemoteException;

	/**
	 * 修改货架位置
	 *
	 * @param code_now
	 *            要转移的货架所在区域
	 * @param row_now
	 *            要转移的货架所在row的编号
	 * @param shelf_now
	 *            要转移的货架的编号
	 * @param code
	 *            目标区域
	 * @param row
	 *            目标row编号
	 * @param shelf
	 *            目标shelf编号
	 * @return 返回操作结果
	 */
	public OperationMessage moveShelf(String centerID,StoreAreaCode code_now, int row_now,
			int shelf_now, StoreAreaCode code, int row, int shelf)
			throws RemoteException;

	public String getLocation(String centerID,StoreAreaCode code, int row,
			int shelf,int position) throws RemoteException;
}
