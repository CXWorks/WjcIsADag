package rmi.deliverdata;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
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
	 * @return 可以被派送的派件清单(订单号列表)
	 */
	public ArrayList<String> available(String HallID) throws RemoteException;

	/**
	 * 按派件员查看派件清单
	 * @param 快递员ID
	 * @return 可以被派送的派件清单(派件单号列表)
	 */
	public ArrayList<String> searchAsPerson(String ID) throws RemoteException;

}
