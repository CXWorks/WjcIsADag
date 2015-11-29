package rmi.financedata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.financedata.RevenuePO;
import po.receivedata.ReceivePO;
import rmi.CommonDataService;

/**
 * 
 * @author wjc
 * @version 2015/11/24
 */

public interface RevenueDataService extends CommonDataService<RevenuePO>{
	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "RevenueData";
	
	public ArrayList<RevenuePO> getByHallID(String ID) throws RemoteException;
}
