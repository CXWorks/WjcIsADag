package rmiImpl.chatRemindImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import message.ChatMessage;
import message.OperationMessage;
import rmi.chatRemindService.ChatNewService;
import rmi.chatRemindService.ChatRemindService;

/**
 * 
 * @author wjc 2015/10/24
 */

public class ChatRemindImpl extends UnicastRemoteObject implements
		ChatRemindService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* String 为员工ID,ArrayList<ChatMessage>为消息集合 */
	static Map<String, ArrayList<ChatMessage>> map;

	public ChatRemindImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		map = new HashMap();
	}

	public OperationMessage checkMessage(String ID) {
		// TODO Auto-generated method stub
		ArrayList<ChatMessage> list = map.get(ID);
		if (list == null)
			return new OperationMessage(false, "没有消息");
		if (list.size() == 0)
			return new OperationMessage(false, "没有消息");
		return new OperationMessage();
	} 

	public ArrayList<ChatMessage> getMessage(String ID) {
		// TODO Auto-generated method stub
		ArrayList<ChatMessage> list = map.get(ID);
		map.put(ID, new ArrayList<ChatMessage>());
		return list;
	}

}
