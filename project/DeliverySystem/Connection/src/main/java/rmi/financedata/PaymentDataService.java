package rmi.financedata;

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
}
