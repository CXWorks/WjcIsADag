package po.accountdata;

import java.io.Serializable;

import po.CommonPO;
import po.FormPO;

public class AccountPO extends CommonPO implements Serializable{

	private String ID;
	private String password;
	private AuthorityEnum authority;
	public AccountPO(){}

	public AccountPO(String ID, AuthorityEnum authority, String password) {
		this.ID = ID;
		this.authority = authority;
		this.password = password;
	}
}
