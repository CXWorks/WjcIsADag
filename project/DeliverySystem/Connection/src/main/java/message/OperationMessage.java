package message;

import java.io.Serializable;

/**
 * Created by Sissel on 2015/10/23.
 */
public class OperationMessage implements Serializable{
	public boolean operationResult;
	private String reason;
	private long cacheVersion;

	public OperationMessage(){
		operationResult=true;
		reason=null;
		cacheVersion=0;
	}
	public OperationMessage(boolean result,String reason){
		this.operationResult=result;
		this.reason=reason;
	}
	
	public void setCacheVersion(long version){
		this.cacheVersion=version;
	}

	public String getReason(){
		return reason;
	}
	public void addReason(String wrongID){
		this.reason += " " + wrongID;
		return;
	}
}
