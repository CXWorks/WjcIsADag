package message;

import java.io.Serializable;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class CheckFormMessage implements Serializable{
	public boolean checkResult;
	private Exception reason;
	public CheckFormMessage(){
		checkResult=true;
	}
}
