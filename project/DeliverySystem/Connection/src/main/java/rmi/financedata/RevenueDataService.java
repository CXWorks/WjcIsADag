package rmi.financedata;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

import message.OperationMessage;
import po.financedata.PaymentPO;
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

	/*
	 * 按照时间获得区间段的所有单
	 */
	public ArrayList<RevenuePO> getByTime(Calendar start, Calendar end) throws RemoteException;
}
