package rmiImpl.chatRemindImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import message.ChatMessage;
import message.OperationMessage;
import rmi.chatRemindService.ChatRemindService;

/**
 *
 * @author wjc 2015/10/24
 */

public class ChatRemindImpl extends UnicastRemoteObject implements ChatRemindService{

	/* String 为员工ID,ArrayList<ChatMessage>为消息集合 */
	static Map<String, ArrayList<ChatMessage>> map;

	public ChatRemindImpl() throws RemoteException {
		super();
		map = new HashMap<String, ArrayList<ChatMessage>>();
	}

	public OperationMessage checkMessage(String ID) {
		ArrayList<ChatMessage> list = map.get(ID);
		if (list == null)
			return new OperationMessage(false, "没有消息");
		if (list.size() == 0)
			return new OperationMessage(false, "没有消息");
		return new OperationMessage(true,"有新的消息");
	}

	public List<ChatMessage> getMessage(String ID) {
		ArrayList<ChatMessage> list = map.get(ID);
		if(list==null)
			list = new ArrayList<ChatMessage>();
		map.put(ID, new ArrayList<ChatMessage>());
		return list;
	}

}
