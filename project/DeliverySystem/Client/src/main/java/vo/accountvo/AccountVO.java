package vo.accountvo;

import po.accountdata.AuthorityEnum;


/**
 * 
 * @author CCharlesMeng
 *2015/10/25
 */

public class AccountVO {
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
		super();
		ID = iD;
		this.password = password;
		this.authority = authority;
	}
	
}
