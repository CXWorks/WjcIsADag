package rmi.companydata;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.HallPO;
/**
 * 
 * @author cxworks
 *2015/10/24
 */
public interface CompanyDataHallService extends Remote{
	/**
	 * 获取营业厅
	 * @return
	 */
	public ArrayList<HallPO> getHall() throws RemoteException;
	/**
	 * 新建营业厅
	 * @param hall
	 * @return
	 */
	public OperationMessage addHall(HallPO hall) throws RemoteException;
	/**
	 * 删除营业厅
	 * @param hall
	 * @return
	 */
	public OperationMessage deleteHall(HallPO hall) throws RemoteException;
	/**
	 * 修改营业厅
	 * @param hall
	 * @return
	 */
	public OperationMessage modifyHall(HallPO hall) throws RemoteException;
	/**
	 * 获取新营业厅ID
	 * @return 返回合法ID
	 */
	public String newHallID() throws RemoteException;
}
