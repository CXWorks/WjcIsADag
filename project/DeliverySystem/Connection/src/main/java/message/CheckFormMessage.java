package message;

import java.io.Serializable;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class CheckFormMessage implements Serializable{
	private boolean checkResult;
	private String reason;
	public CheckFormMessage(){
		checkResult=true;
	}
	public CheckFormMessage(boolean result,String reason){
		this.checkResult=result;
		this.reason=reason;
	}
	public boolean getCheckResult() {
		return checkResult;
	}
	public String getReason() {
		return reason;
	}
	
	
}
