package message;

import java.io.Serializable;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class CheckFormMessage implements Serializable{
	public boolean checkResult;
	private String reason;
	public CheckFormMessage(){
		checkResult=true;
	}
	public CheckFormMessage(boolean result,String reason){
		this.checkResult=result;
		this.reason=reason;
	}
}
