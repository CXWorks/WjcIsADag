package rmi.transportdata;

import java.rmi.Remote;
import java.rmi.RemoteException;

import message.OperationMessage;
import po.transportdata.LoadPO;
import po.transportdata.CenterOutPO;
import po.transportdata.TransportPO;

/**
 * 
 * @author mx
 *2015/10/25
 */

public interface TransportDataService extends Remote{

	/**
	 * 按照快递单号找到装车单/中转单信息
	 * @param id 快递单号
	 * @return 返回目标装车单/中转单信息
	 */
	public TransportPO getTransportPO(String id)throws RemoteException;
	
	/**
	 * 插入新的装车单/中转单信息
	 * @param po 新的装车单/中转单信息
	 * @return 返回操作结果
	 */
	public OperationMessage insert(TransportPO po)throws RemoteException;
	
	/**
	 * 删除装车单/中转单信息
	 * @param id 需被删除的装车单/中转单号
	 * @return 返回操作结果
	 */
	public OperationMessage delete(String id)throws RemoteException;
	
	/**
	 * 更新装车单/中转单信息
	 * @param po 需被更新的装车单/中转单信息
	 * @return 返回操作结果
	 */
	public OperationMessage update(TransportPO po)throws RemoteException;
	
	/**
	 * 清空装车单/中转单数据
	 * @return 返回操作结果
	 */
	public OperationMessage clear()throws RemoteException;

	/**
	 * 返回一个新的装车单/中转单编号
	 * @return 返回操作结果
	 */
	public String newID()throws RemoteException;
}
