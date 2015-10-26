package rmi.orderdata;

import java.rmi.Remote;
import java.rmi.RemoteException;

import message.OperationMessage;
import po.accountdata.AccountPO;
import po.orderdata.OrderPO;

/**
 * 
 * @author mx
 *2015/10/25
 */


public interface OrderDataService extends Remote{

	/**
	 * 按照快递单号找到订单信息
	 * @param id 快递单号
	 * @return 返回目标订单信息
	 */
	public OrderPO getOrderPO(String id)throws RemoteException;
	
	/**
	 * 插入新的订单信息
	 * @param po 新的订单信息
	 * @return 返回操作结果
	 */
	public OperationMessage insert(OrderPO po)throws RemoteException;
	
	/**
	 * 删除订单信息
	 * @param id 需被删除的订单号
	 * @return 返回操作结果
	 */
	public OperationMessage delete(String id)throws RemoteException;
	
	/**
	 * 更新订单信息
	 * @param po 需被更新的订单信息
	 * @return 返回操作结果
	 */
	public OperationMessage update(OrderPO po)throws RemoteException;
	
	/**
	 * 清空订单数据
	 * @return 返回操作结果
	 */
	public OperationMessage clear()throws RemoteException;

	/**
	 * 返回一个新的订单号
	 * @return 返回操作结果
	 */
	public String newID()throws RemoteException;
	
	
}
