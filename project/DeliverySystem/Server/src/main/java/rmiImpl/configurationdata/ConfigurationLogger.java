package rmiImpl.configurationdata;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cache.VersionSaver;
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
	private static final String FILE_NAME="configuration";
	
	public ConfigurationLogger(){
		super(FILE_NAME);
	}
	
}
