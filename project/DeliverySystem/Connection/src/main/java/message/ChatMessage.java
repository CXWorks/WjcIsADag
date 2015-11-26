package message;

import java.io.Serializable;

/**
 * 
 * @author wjc
 *2015/10/24
 */

public class ChatMessage implements Serializable{

	String fromID;
	String toID;
	String message;
	
	public ChatMessage(String fromID, String toID, String message) {
		super();
		this.fromID = fromID;
		this.toID = toID;
		this.message = message;
	}
	
	public String getFromID() {
		return fromID;
	}
	public String getToID() {
		return toID;
	}
	public String getMessage() {
		return message;
	}
	
	
}
