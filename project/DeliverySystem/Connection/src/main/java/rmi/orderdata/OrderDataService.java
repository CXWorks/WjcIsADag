package rmi.orderdata;

import java.rmi.RemoteException;
import java.sql.Connection;

import message.OperationMessage;
import po.orderdata.OrderPO;
import rmi.CommonDataService;

/**
 *
 * @author mx
 *2015/10/25
 */


public interface OrderDataService extends CommonDataService<OrderPO>{

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "OrderDataData";

	public OperationMessage addFormID(String orderID,String formID) throws RemoteException;

	public OperationMessage setFinish(String orderID) throws RemoteException;

}
