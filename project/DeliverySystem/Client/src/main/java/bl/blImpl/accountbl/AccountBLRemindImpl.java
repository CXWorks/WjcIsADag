package bl.blImpl.accountbl;

import java.util.ArrayList;

import message.ChatMessage;
import message.OperationMessage;
import bl.blService.accountblService.AccountBLRemindService;

/** 
 * Client//bl.blImpl.accountbl//AccountBLRemindImpl.java
 * @author CXWorks
 * @date 2015年11月15日 下午3:57:36
 * @version 1.0 
 */
public class AccountBLRemindImpl implements AccountBLRemindService {

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLRemindService#checkMessage(java.lang.String)
	 */
	public OperationMessage checkMessage(String ID) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLRemindService#receive(java.lang.String)
	 */
	public ArrayList<ChatMessage> receive(String ID) {
		// TODO Auto-generated method stub
		return new ArrayList<ChatMessage>();
	}

}
