package vo.accountvo;

import po.CommonPO;
import po.FormEnum;
import po.InfoEnum;
import po.accountdata.AccountPO;
import po.accountdata.AuthorityEnum;
import userinfo.UserInfo;
import vo.InfoVO;

import java.time.*;


/**
 * 
 * @author CCharlesMeng
 *2015/10/25
 */

public class AccountVO extends InfoVO{
	public String ID;
	public String password;
	public AuthorityEnum authority;
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
		AccountPO accountPO= new AccountPO(ID, authority.name(), password);
		accountPO.setCache_OperatorID(UserInfo.getUserID());
		return accountPO;
	}
	//
	public String getID() {
		return ID;
	}

	public String getPassword() {
		return password;
	}

	public AuthorityEnum getAuthority() {
		return authority;
	}
	//
	public boolean fuzzyCheck(String key){
		if (this.ID.contains(key)) {
			return true;
		}
		if (this.password.contains(key)) {
			return true;
		}
		if (this.authority.name().contains(key)) {
			return true;
		}
		return false;
	}
}
