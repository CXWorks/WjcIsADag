package rmi.deliverdata;

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
	
}
