package rmi.accountdata;

import java.util.List;

import message.OperationMessage;
import po.accountdata.AccountPO;

/**
 * 
 * @author mx
 *2015/10/25
 */

public interface AccountDataService {
	/**
	 * 按照账户名查找到账户信息
	 * @param accountID 账户名
	 * @return 返回目标账户信息
	 */
	public AccountPO getAccountPO(String accountID);
	
	/**
	 * 直接获得所有账户信息
	 * @param 无
	 * @return 返回所有账户信息
	 */
	public List<AccountPO> getAccountPOs();

	/**
	 * 插入新的账户信息
	 * @param po 新的账户信息
	 * @return 返回操作结果
	 */
	public OperationMessage insert(AccountPO po);
	
	/**
	 * 删除账户信息
	 * @param accountID 需被删除的账户账户名
	 * @return 返回操作结果
	 */
	public OperationMessage delete(String accountID);
	
	/**
	 * 更新账户信息
	 * @param po 需被更新的账户信息
	 * @return 返回操作结果
	 */
	public OperationMessage update(AccountPO po);
	
	/**
	 * 检查账户名是否存在
	 * @param accountID 需检查的账户名
	 * @return 返回是否存在，是，返回true，否，返回false
	 */
	public Boolean checkID(String accountID);

	/**
	 * 检查账户名和密码是否匹配
	 * @param id 账户名    ；  password 密码
	 * @return 返回是否匹配，是，返回true，否，返回false
	 */
	public Boolean checkAccount(String id , String password);
	
	/**
	 * 返回一个新的账户名
	 * @return 返回操作结果
	 */
	public String newAccountID();
	
	
	
}
