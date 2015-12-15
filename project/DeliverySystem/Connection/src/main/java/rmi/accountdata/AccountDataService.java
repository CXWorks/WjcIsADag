package rmi.accountdata;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;





import message.OperationMessage;
import po.accountdata.AccountPO;
import rmi.DataService;

/**
 *
 * @author mx
 *2015/10/25
 */

public interface AccountDataService extends DataService<AccountPO> {

	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "AccountData";

	/**
	 * 按照账户名查找到账户信息
	 * @param accountID 账户名
	 * @return 返回目标账户信息
	 */
	public AccountPO getAccountPO(String accountID)throws RemoteException;

	/**
	 * 直接获得所有账户信息
	 * @param 无
	 * @return 返回所有账户信息
	 */
	public ArrayList<AccountPO> getAccountPOs()throws RemoteException;

	/**
	 * 插入新的账户信息
	 * @param po 新的账户信息
	 * @return 返回操作结果
	 */
	public OperationMessage insert(AccountPO po)throws RemoteException;

	/**
	 * 删除账户信息
	 * @param accountID 需被删除的账户账户名
	 * @return 返回操作结果
	 */
	public OperationMessage delete(String accountID)throws RemoteException;

	/**
	 * 更新账户信息
	 * @param po 需被更新的账户信息
	 * @return 返回操作结果
	 */
	public OperationMessage update(AccountPO po)throws RemoteException;


	/**
	 * 检查账户名是否存在以及和密码是否匹配
	 * @param id 账户名    ；  password 密码
	 * @return 返回是否匹配，是，返回true，否，返回false
	 */
	public OperationMessage checkAccount(String id , String password)throws RemoteException;

	public OperationMessage setAccount(String id , boolean isOnline)throws RemoteException;

	/**
	 * 获得新的编号
	 * @param type 工种    ； city 城市
	 * @return 返回新编号
	 */
//	public String newAccountID(String type,String city);
}
