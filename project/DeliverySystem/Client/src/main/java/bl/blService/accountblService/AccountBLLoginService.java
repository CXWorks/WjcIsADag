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
	
	

}
