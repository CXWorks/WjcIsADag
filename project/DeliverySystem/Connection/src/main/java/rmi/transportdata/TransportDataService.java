package rmi.transportdata;

import java.rmi.Remote;
import java.rmi.RemoteException;

import message.OperationMessage;
import po.receivedata.ReceivePO;
import po.transportdata.LoadPO;
import po.transportdata.CenterOutPO;
import po.transportdata.TransportPO;
import rmi.CommonDataService;

/**
 * 
 * @author mx
 *2015/10/25
 */

public interface TransportDataService extends CommonDataService<TransportPO>{

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "TransportData";
}
