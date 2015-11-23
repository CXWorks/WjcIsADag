package bl.blImpl.accountbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import rmi.accountdata.AccountDataService;
import rmi.chatRemindService.ChatRemindService;
import message.ChatMessage;
import message.OperationMessage;
import bl.blService.accountblService.AccountBLRemindService;
import bl.clientNetCache.CacheHelper;

/** 
 * Client//bl.blImpl.accountbl//AccountBLRemindImpl.java
 * @author CXWorks
 * @date 2015年11月15日 下午3:57:36
 * @version 1.0 
 */
public class AccountBLRemindImpl implements AccountBLRemindService {
	ChatRemindService chatRemindService;
	public AccountBLRemindImpl(){
		this.chatRemindService=CacheHelper.getChatRemindService();
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLRemindService#checkMessage(java.lang.String)
	 */
	public OperationMessage checkMessage(String ID) {
		try {
			return chatRemindService.checkMessage(ID);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLRemindService#receive(java.lang.String)
	 */
	public ArrayList<ChatMessage> receive(String ID) {
		try {
			return chatRemindService.receive(ID);
		} catch (RemoteException e) {
			return null;
		}
	}

}
