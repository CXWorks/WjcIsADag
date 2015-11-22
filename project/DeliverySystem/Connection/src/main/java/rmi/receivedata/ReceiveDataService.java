package rmi.receivedata;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
import po.receivedata.ReceivePO;
import po.receivedata.StateEnum;
import rmi.CommonDataService;

/**
 * 
 * @author wjc
 * @version 2015/10/24
 */

public interface ReceiveDataService extends CommonDataService<ReceivePO>{
	
	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "ReceiveData";

}
