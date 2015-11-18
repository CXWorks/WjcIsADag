package po.accountdata;

import java.io.Serializable;

import javax.sound.midi.MidiDevice.Info;

import po.CommonPO;
import po.FormPO;
import po.InfoPO;

public class AccountPO extends InfoPO implements Serializable{

	private String ID;
	private String password;
	private AuthorityEnum authority;
	public AccountPO(){}

	public AccountPO(String ID, AuthorityEnum authority, String password) {
		this.ID = ID;
		this.authority = authority;
		this.password = password;
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
	
}
