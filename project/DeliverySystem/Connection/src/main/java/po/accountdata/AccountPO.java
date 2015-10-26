package po.accountdata;

import java.io.Serializable;

import po.FormPO;

public class AccountPO implements Serializable{

	private String ID;
	private String password;
	private AuthorityEnum authority;
}
