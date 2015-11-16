package bl.blImpl.accountbl;

import java.rmi.RemoteException;

import message.OperationMessage;
import bl.blService.accountblService.AccountBLLoginService;
import bl.clientNetCache.CacheHelper;

/** 
 * Client//bl.blImpl.accountbl//AccountBLLoginServiceImpl.java
 * @author CXWorks
 * @date 2015年11月15日 下午3:54:17
 * @version 1.0 
 */
public class AccountBLLoginImpl implements AccountBLLoginService {
	
	

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLLoginService#checkAccountat(java.lang.String, java.lang.String)
	 */
	public OperationMessage checkAccount(String id, String password) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

}
