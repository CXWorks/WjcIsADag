package rmi.deliverdata;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.deliverdata.DeliverPO;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public interface DeliverDataService extends Remote{
	/**
	 * 插入新的派件信息
	 * @param po 新的派件信息
	 * @return 返回操作结果
	 */
	public OperationMessage insert(DeliverPO po) throws RemoteException;

	/**
	 * 按派件单号查找到达信息
	 * @param id 派件单编号
	 * @return 返回目标派件信息
	 */
	public DeliverPO getDeliverPO(String id) throws RemoteException;
	
	/**
	 * 删除派件信息
	 * @param id 需被删除的派件信息对应派件单号
	 * @return 返回操作结果
	 */
	public OperationMessage delete(String id) throws RemoteException;

	/**
	 * 更新派件信息
	 * @param po 需被更新的派件信息
	 * @return 返回操作结果
	 */
	public OperationMessage update(DeliverPO po) throws RemoteException;

	/**
	 * 清空到达信息
	 * @param id 需被清空的派件信息对应到派件号
	 * @return 返回操作结果
	 */
	public OperationMessage init(String id) throws RemoteException;
	
	/**
	 * 获得新的派件单编号
	 * @return 新的派件单编号
	 */
	public String newID() throws RemoteException;
	
	/**
	 * 清空派件数据
	 * @return 返回操作结果
	 */
	public OperationMessage clear() throws RemoteException;
	
	/**
	 * 展示全部派件信息
	 * @return 所有派件信息
	 */
	public ArrayList<DeliverPO> show() throws RemoteException;
}
