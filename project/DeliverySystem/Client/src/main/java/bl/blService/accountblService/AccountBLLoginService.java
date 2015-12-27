package bl.blService.accountblService;

import message.OperationMessage;

/** 
 * Client//bl.blService.accountblService//AccountblLoginService.java
 * @author CXWorks
 * @date 2015年11月15日 下午3:48:49
 * @version 1.0 
 */
public interface AccountBLLoginService {
	/**
	 * 检查用户名是否存在以及和密码是否匹配
	 * @param id 输入的用户名  password 输入的密码
	 * @return 返回操作结果
	 */
	public OperationMessage checkAccount(String id,String password);
	
	public OperationMessage logOut();
	/**
	 * 设置网络IP
	 * @param ip
	 * @return
	 */
	public OperationMessage setIP(String ip);
	/**
	 * 设置端口号
	 * @param port
	 * @return
	 */
	public OperationMessage setPort(String port);
	/**
	 * 设置存储路径
	 * @warning 可能会很晚才实现
	 * @param path
	 * @return
	 */
	public OperationMessage setWorkspace(String path);

}
