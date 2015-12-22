package bl.clientNetCache.dataProxy;

import java.rmi.RemoteException;
import java.util.List;

import operation.Operation;

/** 
 * Client//bl.clientNetCache.dataProxy//DataProxy.java
 * @author CXWorks
 * @date 2015年12月21日 下午10:40:14
 * @version 1.0 
 */
public abstract class DataProxy {
	protected abstract void dealOperation(List<Operation> operations) throws RemoteException;
}
