package rmi.transportdata;

import java.sql.Connection;

import po.transportdata.CenterOutPO;
import po.transportdata.TransportPO;
import rmi.CommonDataService;

/**
 * 
 * @author mx
 *2015/10/25
 */

public interface CenterOutDataService extends CommonDataService<CenterOutPO>{

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "CenterOutData";

}
