package rmi.companydata;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.*;
import rmi.DataService;
import rmi.cachedata.CacheDataService;
/**
 *
 * @author cxworks
 *2015/10/24
 */
public interface CompanyDataCenterService extends CacheDataService,DataService<CenterPO>{

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "CompanyDataCenter";
	/**
	 *
	 * @return
	 */
	public CenterPO getCenterByID(String ID) throws RemoteException;
	/**
	 *
	 * @return
	 */
	public ArrayList<CenterPO> getCenter() throws RemoteException;
	/**
	 *
	 * @param center
	 * @return
	 */
	public OperationMessage addCenter(CenterPO center) throws RemoteException;
	/**
	 *
	 * @param center
	 * @return
	 */
	public OperationMessage deleteCenter(CenterPO center) throws RemoteException;
	/**
	 *
	 * @param center
	 * @return
	 */
	public OperationMessage modifyCenter(CenterPO center) throws RemoteException;

	/**
	 * 新建中转中心
	 * @param city 城市
	 * @return 新的中转中心编号
	 */
	public String newCenterID(String city) throws RemoteException;

}
