package rmiImpl;

import java.util.ArrayList;

import operation.Operation;
import cache.CacheLogService;
import cache.VersionSaver;
import rmi.cachedata.CacheDataAbstract;
import rmi.cachedata.CacheDataService;

/** 
 * Server//rmiImpl//Logger.java
 * @author CXWorks
 * @date 2015年12月13日 上午9:51:53
 * @version 1.0 
 */
public abstract class Logger extends CacheDataAbstract implements CacheLogService{
	protected Logger(String fileName){
		this.version=0L;
		VersionSaver versionSaver=new VersionSaver();
		this.version=versionSaver.loadVersion(fileName);
		this.cacheData=new ArrayList<Operation>(20);
	}
	/* (non-Javadoc)
	 * @see cache.CacheLogService#addNewOperation(operation.Operation)
	 */
	@Override
	public long addNewOperation(Operation operation) {
		this.version++;
		operation.version=this.version;
		this.cacheData.add(operation);
		return this.version;
	}
}
