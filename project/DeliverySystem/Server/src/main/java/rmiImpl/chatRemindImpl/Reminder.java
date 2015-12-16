package rmiImpl.chatRemindImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import message.ChatMessage;
import message.OperationMessage;
import rmi.chatRemindService.ChatNewService;
/**
 * 负责在客户端产生消息
 * @author wjc 2015/11/26
 */
public class Reminder extends UnicastRemoteObject implements  ChatNewService {

	public Reminder() throws RemoteException {
		super();
	}

	@Override
	public OperationMessage add(String ID, ChatMessage mes)
			throws RemoteException {
		OperationMessage result = new OperationMessage();
		ArrayList<ChatMessage> list = ChatRemindImpl.map.get(ID);
		if (list == null) {
			list = new ArrayList<ChatMessage>();
		}
		list.add(mes);
		ChatRemindImpl.map.put(ID, list);
		return result;
	}

}
