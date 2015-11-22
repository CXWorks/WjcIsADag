package rmi;

import java.rmi.Remote;
import java.sql.Connection;

import po.CommonPO;
import po.FormPO;

/**
 * 所有data接口的父类
 * @author wjc
 * @version 2015.10.31
 */
public interface DataService<PO extends CommonPO > extends Remote {
	
//	/**
//	 * 获得Connection
//	 * @return Connection
//	 */
//	public Connection getConn();
}
