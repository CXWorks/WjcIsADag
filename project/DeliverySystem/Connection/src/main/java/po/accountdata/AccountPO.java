package po.accountdata;

import java.io.Serializable;

import javax.sound.midi.MidiDevice.Info;

import po.CommonPO;
import po.FormPO;
import po.InfoEnum;
import po.InfoPO;
import po.receivedata.StateEnum;
import util.DataType;

public class AccountPO extends InfoPO implements Serializable{

	private String ID;
	private String password;
	private AuthorityEnum authority;
	public AccountPO(){
		super(InfoEnum.ACCOUNT);
	}

	public AccountPO(String ID, String authority, String password) {
		this();
		this.ID = ID;
		this.setAuthority(authority);
		this.password = password;
		this.dataType = DataType.DATA;
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

	public void setAuthority(String authority) {
		if(authority.equalsIgnoreCase("DONT_HAVE"))
			this.authority = AuthorityEnum.DONT_HAVE;
		else if(authority.equalsIgnoreCase("HAVE"))
			this.authority = AuthorityEnum.HAVE;
	}
}
