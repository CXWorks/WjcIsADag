package bl.blService.accountblService;

import java.util.ArrayList;

import po.accountdata.AccountPO;
import message.OperationMessage;
import vo.accountvo.AccountVO;

/**
 * 
 * @author mx
 *2015/10/25
 */
public interface AccountBLService {
	
	/**
	 * 显示账户信息
	 * @return 返回所有账户信息
	 */
	public ArrayList<AccountVO> getAccountVOs();
	
	/**
	 * 显示ID对应账户信息
	 * @param accountID 账户名
	 * @return 返回ID对应账户信息
	 */
	public AccountVO getAccountVO(String accountID);
	
	/**
	 * 添加账户
	 * @param po 新增账户信息
	 * @return 返回操作结果
	 */
	public OperationMessage addAccount(AccountPO po);
	
	/**
	 * 删除账户
	 * @param po 需删除账户信息
	 * @return 返回操作结果
	 */
	public OperationMessage deleteAccount(AccountPO po);
	
	/**
	 * 修改账户
	 * @param po 已修改账户信息
	 * @return 返回操作结果
	 */
	public OperationMessage modifyAccount(AccountPO po);
	
	/**
	 * 检查用户名是否存在以及和密码是否匹配
	 * @param id 输入的用户名  password 输入的密码
	 * @return 返回操作结果
	 */
	public OperationMessage checkAccountat(String id,String password);
	

}
