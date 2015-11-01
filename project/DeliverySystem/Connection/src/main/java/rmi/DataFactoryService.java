package rmi;

import java.rmi.RemoteException;

import rmi.chatRemindService.ChatRemindService;
import rmi.examineService.ExamineManageService;
import rmi.examineService.ExamineSubmitService;

/**
 * 数据层，接口处理器
 * @author wjc
 * @version 2014.10.31
 */
public interface DataFactoryService {
	
	/**
	 * 注意：在jdk1.7之后的版本才支持case String
	 * @param name data的名字
	 * @return DataService的子类实现
	 * @throws RemoteException
	 * @author wjc
	 * @version 2014.10.31
	 */
	public DataService<?> createDataService(String name) throws RemoteException;
	
	/**
	 * @return ChatRemindService
	 * @throws RemoteException
	 * @author wjc
	 * @version 2014.10.31
	 */
	public ChatRemindService creatChatRemindService() throws RemoteException;
	
	/**
	 * @return ExamineSubmitService
	 * @throws RemoteException
	 * @author wjc
	 * @version 2014.10.31
	 */
	public ExamineSubmitService creatExamineSubmitService() throws RemoteException;
	
	/**
	 * @return ExamineManageService
	 * @throws RemoteException
	 * @author wjc
	 * @version 2014.10.31
	 */
	public ExamineManageService creatExamineManageService() throws RemoteException;
	
}
