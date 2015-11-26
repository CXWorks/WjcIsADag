package rmi.chatRemindService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import message.ChatMessage;
import message.OperationMessage;

public interface ChatNewService extends Remote{
	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "ChatRemind";
	
	/**
	 * 服务器端消息缓存增加
	 * @param ID 目标员工的ID
	 * @param mes 消息
	 * @return 返回消息列表
	 */
	public OperationMessage add(String ID,ChatMessage mes) throws RemoteException;
}
