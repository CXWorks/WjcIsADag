package bl.blService.accountblService;

import java.util.ArrayList;

import message.ChatMessage;
import message.OperationMessage;

/** 
 * Client//bl.blService.accountblService//AccountBLRemindService.java
 * @author CXWorks
 * @date 2015年11月15日 下午3:50:19
 * @version 1.0 
 */
public interface AccountBLRemindService {
	/**
	 * 检查是否有新消息
	 * @param ID
	 * @return
	 */
	public OperationMessage checkMessage(String ID);
	/**
	 * 返回ID对应的新消息
	 * @param ID
	 * @return
	 */
	public ArrayList<ChatMessage> receive(String ID);

}
