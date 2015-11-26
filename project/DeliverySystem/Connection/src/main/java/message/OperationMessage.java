package message;

import java.io.Serializable;

/**
 * Created by Sissel on 2015/10/23.
 */
public class OperationMessage implements Serializable{
	public boolean operationResult;
	private String reason;

	public OperationMessage(){
		operationResult=true;
	}
	public OperationMessage(boolean result,String reason){
		this.operationResult=result;
		this.reason=reason;
	}

	public String getReason(){
		return reason;
	}
	public void addReason(String wrongID){
		this.reason += " " + wrongID;
		return;
	}
}
