package rmi.deliverdata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.deliverdata.DeliverPO;
import rmi.CommonDataService;

/**
 * 
 * @author wjc
 * @version 2015/10/24
 */

public interface DeliverDataService extends CommonDataService<DeliverPO>{
	
	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "DeliverData";
	
	/**
	 * 查找可以被派送的派件清单
	 * @param 营业厅ID
	 * @return 可以被派送的派件清单
	 */
	public ArrayList<DeliverPO> available(String HallID) throws RemoteException;
	
}
