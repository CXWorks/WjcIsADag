package bl.blImpl.accountbl;

import java.rmi.RemoteException;
import java.util.List;

import rmi.chatRemindService.ChatRemindService;
import message.OperationMessage;
import bl.NetReconnect.ReconnectMe;
import bl.blService.accountblService.AccountBLRemindService;
import bl.clientNetCache.CacheHelper;
import message.ChatMessage;

/** 
 * Client//bl.blImpl.accountbl//AccountBLRemindImpl.java
 * @author CXWorks
 * @date 2015年11月15日 下午3:57:36
 * @version 1.0 
 */
public class AccountBLRemindImpl implements AccountBLRemindService {

	public AccountBLRemindImpl(){
		
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLRemindService#checkMessage(java.lang.String)
	 */
	public OperationMessage checkMessage(String ID) {
		ChatRemindService chatRemindService=CacheHelper.getChatRemindService();
		try {
			return chatRemindService.checkMessage(ID);
		} catch (RemoteException e) {
			ReconnectMe.ReConnectFactory();
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLRemindService#receive(java.lang.String)
	 */
	public List<ChatMessage> receive(String ID) {
		ChatRemindService chatRemindService=CacheHelper.getChatRemindService();
		try {
			return chatRemindService.getMessage(ID);
		} catch (RemoteException e) {
			ReconnectMe.ReConnectFactory();
			return null;
		}
	}

}
