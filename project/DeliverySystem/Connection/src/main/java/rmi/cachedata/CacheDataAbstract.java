package rmi.cachedata;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import operation.Operation;

/** 
 * Connection//rmi.cachedata//CacheDataAbstract.java
 * @author CXWorks
 * @date Dec 23, 2015 11:56:18 PM
 * @version 1.0 
 */
public abstract class CacheDataAbstract implements CacheDataService {
	protected long version;
	protected List<Operation> cacheData;

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
