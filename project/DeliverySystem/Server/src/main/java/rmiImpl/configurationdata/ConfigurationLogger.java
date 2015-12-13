package rmiImpl.configurationdata;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import operation.Operation;
import rmi.cachedata.CacheDataService;
import rmiImpl.Logger;

/** 
 * Server//rmiImpl.configurationdata//ConfigurationLogger.java
 * @author CXWorks
 * @date 2015年12月13日 上午9:52:40
 * @version 1.0 
 */
public class ConfigurationLogger extends Logger {
	private long version ;
	private List<Operation> cacheData;

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
	
}
