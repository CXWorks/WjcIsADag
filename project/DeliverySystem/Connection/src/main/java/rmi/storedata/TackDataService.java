package rmi.storedata;

import java.rmi.RemoteException;

import message.OperationMessage;
import po.CommonPO;
import rmi.DataService;

/**
 * Created by WJC on 2015/12/24.
 */
public interface TackDataService extends DataService<CommonPO>{

	/**
	 * 获得仓库上一次（最新）盘点的序号
	 * @param centerID 仓库编号
	 * @return 该仓库上一次（最新）盘点的序号
	 */
	public int getTack(String centerID) throws RemoteException;

	/**
	 * 设置仓库最新盘点的序号
	 * @param centerID 仓库编号 num 新的编号
	 * @return 操作信息
	 */
	public OperationMessage setTack(String centerID,String num) throws RemoteException;

	/**
	 * 获得新编号
	 * @param centerID 仓库编号
	 * @return 新的编号
	 */
	public int newTackNum(String centerID) throws RemoteException;
}
