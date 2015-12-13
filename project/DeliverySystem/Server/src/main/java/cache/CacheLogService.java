package cache;

import operation.Operation;

/** 
 * Server//cache//CacheLogService.java
 * @author CXWorks
 * @date 2015年12月13日 上午11:11:49
 * @version 1.0 
 */
public interface CacheLogService {
	/**
	 * 添加新的操作到logger
	 * @param operation 
	 * @return 新的版本号
	 */
	public long addNewOperation(Operation operation);
}
