package rmi.cachedata;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import operation.Operation;

/** 
 * Connection//rmi.cachedata//CacheDataService.java
 * @author CXWorks
 * @date 2015年12月13日 上午9:25:53
 * @version 1.0 
 */
public interface CacheDataService extends Remote{
	/**
	 * 获取最新版本号
	 * @return
	 * @throws RemoteException
	 */
	public default long getLatestVersionID() throws RemoteException{
		return 0;
	};
	/**
	 * 通过本地版本号获取操作
	 * @param localVersion
	 * @return	
	 * @throws RemoteException
	 */
	public default List<Operation> getOperation(long localVersion) throws RemoteException{
		return null;
	};
	
	
}
