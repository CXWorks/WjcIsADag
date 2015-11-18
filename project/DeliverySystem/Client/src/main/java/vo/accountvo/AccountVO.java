package vo.accountvo;

import po.FormEnum;
import po.accountdata.AccountPO;
import po.accountdata.AuthorityEnum;
import vo.InfoVO;


/**
 * 
 * @author CCharlesMeng
 *2015/10/25
 */

public class AccountVO extends InfoVO{
	private String ID;
	private String password;
	private AuthorityEnum authority;
	public AccountVO(){}
	//
	/**
	 * @param iD
	 * @param password
	 * @param authority
	 */
	public AccountVO(String iD, String password, AuthorityEnum authority) {
		//super(FormEnum);
		ID = iD;
		this.password = password;
		this.authority = authority;
	}
	//
	public AccountVO(AccountPO po){
		
	}
}
