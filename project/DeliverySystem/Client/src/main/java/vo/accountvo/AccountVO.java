package vo.accountvo;

import po.FormEnum;
import po.InfoEnum;
import po.accountdata.AccountPO;
import po.accountdata.AuthorityEnum;
import vo.InfoVO;
import java.time.*;


/**
 * 
 * @author CCharlesMeng
 *2015/10/25
 */

public class AccountVO extends InfoVO{
	private String ID;
	private String password;
	private AuthorityEnum authority;
	public AccountVO(){
		super(InfoEnum.ACCOUNT);
	}
	//
	/**
	 * @param iD
	 * @param password
	 * @param authority
	 */
	public AccountVO(String iD, String password, AuthorityEnum authority) {
		super(InfoEnum.ACCOUNT);
		ID = iD;
		this.password = password;
		this.authority = authority;
	}
	//
	public AccountVO(AccountPO po){
		this(po.getID()	, po.getPassword(), po.getAuthority());
	}
	//
	public AccountPO toPO(){
		return new AccountPO(ID, authority.name(), password);
	}
}
