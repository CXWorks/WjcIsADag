package rmi.companydata;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.HallPO;
import rmi.DataService;
import rmi.cachedata.CacheDataService;
/**
 *
 * @author cxworks
 *2015/10/24
 */
public interface CompanyDataHallService extends DataService<HallPO>,CacheDataService{

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "CompanyDataHall";
	/**
	 * 获取营业厅
	 * @return
	 */
	public HallPO getHallByID(String ID) throws RemoteException;
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
	 * @param 城市编号
	 * @return 返回合法ID
	 */
	public String newHallID(String city) throws RemoteException;
}
