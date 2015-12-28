package rmi.transportdata;

import java.rmi.RemoteException;
import java.sql.Connection;

import po.transportdata.LoadPO;
import rmi.CommonDataService;

/**
 *
 * @author wjc
 *2015/11/20
 */

public interface LoadDataService extends CommonDataService<LoadPO> {

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "LoadData";

	/**
	 * 获得新的汽运编号
	 * @param unitID 机构编号
	 * @return 新的汽运编号
	 */
	public String newTransID(String unitID) throws RemoteException;
}
