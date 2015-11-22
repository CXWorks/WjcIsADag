package rmi.transportdata;

import java.sql.Connection;

import po.transportdata.LoadPO;
import rmi.CommonDataService;

/**
 * 
 * @author wjc
 *2015/11/20
 */

public interface LoadDataService extends CommonDataService<LoadPO> {

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "LoadData";

}
