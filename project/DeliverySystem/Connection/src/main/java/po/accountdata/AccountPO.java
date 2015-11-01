package po.accountdata;

import java.io.Serializable;

import po.CommonPO;
import po.FormPO;

public class AccountPO extends CommonPO implements Serializable{

	private String ID;
	private String password;
	private AuthorityEnum authority;
}
