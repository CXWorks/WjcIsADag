package rmi.storedata;

import po.CommonPO;
import po.FormPO;
import po.storedata.StoreInPO;
import po.storedata.StoreOutPO;
import rmi.DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import message.OperationMessage;

/**
 * Created by Sissel on 2015/10/23.
 */
public interface StoreFormDataService extends DataService<CommonPO>{

    /**
	 * 插入新的in信息
	 * @param po 新的信息
	 * @return 返回操作结果
	 */
	public OperationMessage insertStoreInPO(StoreInPO po) throws RemoteException;
    /**
	 * 插入新的out信息
	 * @param po 新的信息
	 * @return 返回操作结果
	 */
	public OperationMessage insertStoreOutPO(StoreOutPO po) throws RemoteException;
	/**
	 * 删除in信息
	 * @param id 需被删除的信息对应单号
	 * @return 返回操作结果
	 */
	public OperationMessage deleteStoreInPO(String id) throws RemoteException;
	/**
	 * 删除out信息
	 * @param id 需被删除的信息对应单号
	 * @return 返回操作结果
	 */
	public OperationMessage deleteStoreOutPO(String id) throws RemoteException;
	/**
	 * 更新in信息
	 * @param id 需被更新的信息对应单号
	 * @return 返回操作结果
	 */
	public OperationMessage updateStoreInPO(StoreInPO po) throws RemoteException;
	/**
	 * 更新out信息
	 * @param id 需被更新的信息对应单号
	 * @return 返回操作结果
	 */
	public OperationMessage updateStoreOutPO(StoreOutPO po) throws RemoteException;
	/**
	 * 清空in数据
	 * @return 返回操作结果
	 */
	public OperationMessage clearStoreInPO() throws RemoteException;
	/**
	 * 清空out数据
	 * @return 返回操作结果
	 */
	public OperationMessage clearStoreOutPO() throws RemoteException;
	/**
	 * 获得新的in单编号
	 * @param unitID 开具单据的营业厅或者中转中心编号（无则填null）
	 * @return 新的单编号
	 */
	public String newIDStoreInPO(String unitID) throws RemoteException;
	/**
	 * 获得新的out单编号
	 * @param unitID 开具单据的营业厅或者中转中心编号（无则填null）
	 * @return 新的单编号
	 */
	public String newIDStoreOutPO(String unitID) throws RemoteException;
	/**
	 * 按单号查找in信息
	 * @param id 单编号
	 * @return 返回目标信息
	 */
	public StoreInPO getStoreInPO(String id) throws RemoteException;
	/**
	 * 按单号查找out信息
	 * @param id 单编号
	 * @return 返回目标信息
	 */
	public StoreOutPO getStoreOutPO(String id) throws RemoteException;
	/**
	 * 展示全部in信息
	 * @return 所有信息
	 */
	public ArrayList<StoreInPO> getAllStoreInPO() throws RemoteException;
	/**
	 * 展示全部out信息
	 * @return 所有信息
	 */
	public ArrayList<StoreOutPO> getAllStoreOutPO() throws RemoteException;
	/**
	 * 查看某一时间段的出入记录
	 * @return 所有信息
	 */
	public ArrayList<FormPO> getInOutInfo(Calendar start,Calendar end) throws RemoteException;

	/**
	 * 按照入库单的提交者查找历史
	 * @return 对应查找信息
	 */
	public List<StoreInPO> getHistoryIn(String creatorID) throws RemoteException;

	/**
	 * 按照出库单的提交者查找历史
	 * @return 对应查找信息
	 */
	public List<StoreOutPO> getHistoryOut(String creatorID) throws RemoteException;
}
