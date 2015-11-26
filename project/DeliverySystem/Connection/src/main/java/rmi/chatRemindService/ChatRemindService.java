package rmi.chatRemindService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.ChatMessage;
import message.OperationMessage;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public interface ChatRemindService extends Remote{

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "ChatRemind";
	
	/**
	 * 检查数据接口是否存在未读的系统通知
	 * @param ID 员工的ID
	 * @return 返回操作结果
	 */
	public OperationMessage checkMessage(String ID) throws RemoteException;
	
	/**
	 * 接收信息
	 * @param ID 员工的ID
	 * @return 返回消息列表
	 */
	public ArrayList<ChatMessage> getMessage(String ID) throws RemoteException;
	/**
	 * 服务器端消息缓存增加
	 * @param ID 目标员工的ID
	 * @param mes 消息
	 * @return 返回消息列表
	 */
	public OperationMessage add(String ID,ChatMessage mes) throws RemoteException;
}
