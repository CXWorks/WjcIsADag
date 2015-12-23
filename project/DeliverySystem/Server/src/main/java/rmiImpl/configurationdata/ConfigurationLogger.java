package rmiImpl.configurationdata;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cache.VersionSaver;
import operation.Operation;
import rmiImpl.Logger;

/**
 * Server//rmiImpl.configurationdata//ConfigurationLogger.java
 * @author CXWorks
 * @date 2015年12月13日 上午9:52:40
 * @version 1.0
 */
public class ConfigurationLogger extends Logger {
	private static final String FILE_NAME="configuration";
	private long version ;
	private List<Operation> cacheData;

	public ConfigurationLogger(){
		this.version=0;
		VersionSaver versionSaver=new VersionSaver();
		this.version=versionSaver.loadVersion(FILE_NAME);
		this.cacheData=new ArrayList<Operation>(20);
	}

	/* (non-Javadoc)
	 * @see rmi.cachedata.CacheDataService#getLatestVersionID()
	 */
	@Override
	public long getLatestVersionID() throws RemoteException {
		return this.version;
	}

	/* (non-Javadoc)
	 * @see rmi.cachedata.CacheDataService#getOperation(long)
	 */
	@Override
	public List<Operation> getOperation(long localVersion)
			throws RemoteException {
		List<Operation> ans=
				cacheData.stream().filter(operation->{return operation.version>localVersion;})
				.collect(Collectors.toList());
		//
		return ans;
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
