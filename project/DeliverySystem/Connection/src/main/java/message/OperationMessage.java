package message;

import java.io.Serializable;

/**
 * Created by Sissel on 2015/10/23.
 */
public class OperationMessage implements Serializable{
	public boolean operationResult;
	private Exception reason;
	public OperationMessage(){
		operationResult=true;
	}
}
