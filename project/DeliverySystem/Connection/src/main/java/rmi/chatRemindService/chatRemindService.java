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

public interface chatRemindService extends Remote{

	/**
	 * 检查数据接口是否存在未读的系统通知
	 * @param ID 员工的ID
	 * @return 返回操作结果
	 */
	public OperationMessage checkMessage(String ID) throws RemoteException;
	
	/**
	 * 传输消息
	 * @param mes 传送的消息, ID 员工的ID
	 * @return 返回操作结果
	 */
	public OperationMessage transfer(ArrayList<ChatMessage>mes,String ID) throws RemoteException;
	
	/**
	 * 接收信息
	 * @param ID 员工的ID
	 * @return 返回消息列表
	 */
	public ArrayList<ChatMessage> receive(String ID) throws RemoteException;
}
