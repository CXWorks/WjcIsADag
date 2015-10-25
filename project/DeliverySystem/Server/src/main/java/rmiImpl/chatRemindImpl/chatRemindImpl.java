package rmiImpl.chatRemindImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import message.ChatMessage;
import message.OperationMessage;
import rmi.chatRemindService.chatRemindService;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class chatRemindImpl extends UnicastRemoteObject implements chatRemindService {

	protected chatRemindImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public OperationMessage checkMessage(String ID) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage transfer(ArrayList<ChatMessage> mes, String ID) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public ArrayList<ChatMessage> receive(String ID) {
		// TODO Auto-generated method stub
		ArrayList<ChatMessage> result =new ArrayList<ChatMessage>();
		ChatMessage stub=new ChatMessage();
		result.add(stub);
		return result;
	}

}
