package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.FormPO;
import po.receivedata.ReceivePO;

/**
 * @author wjc
 * @version 2014.10.31
 */
public interface CommonDataService<PO extends FormPO> extends DataService<PO>{
	/**
	 * 插入新的信息
	 * @param po 新的信息
	 * @return 返回操作结果
	 */
	public OperationMessage insert(PO po) throws RemoteException;

	/**
	 * 按到达单号查找信息
	 * @param id 单编号
	 * @return 返回目标信息
	 */
	public PO getFormPO(String id) throws RemoteException;
	
	/**
	 * 删除信息
	 * @param id 需被删除的信息对应单号
	 * @return 返回操作结果
	 */
	public OperationMessage delete(String id) throws RemoteException;

	/**
	 * 更新信息
	 * @param id 需被更新的信息对应单号
	 * @return 返回操作结果
	 */
	public OperationMessage update(PO po) throws RemoteException;
	
	/**
	 * 获得新的单编号
	 * @return 新的单编号
	 */
	public String newID() throws RemoteException;
	
	/**
	 * 清空数据
	 * @return 返回操作结果
	 */
	public OperationMessage clear() throws RemoteException;
	
	/**
	 * 展示全部信息
	 * @return 所有信息
	 */
	public ArrayList<PO> getAll() throws RemoteException;
}
