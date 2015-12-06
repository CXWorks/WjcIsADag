package rmi.financedata;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

import po.financedata.PaymentPO;
import rmi.CommonDataService;

/**
 *
 * @author wjc
 * @version 2015/11/24
 */
public interface PaymentDataService extends CommonDataService<PaymentPO>{
	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "PaymentData";

	/*
	 * 按照时间获得区间段的所有单
	 */
	public ArrayList<PaymentPO> getByTime(Calendar start, Calendar end) throws RemoteException;
}
