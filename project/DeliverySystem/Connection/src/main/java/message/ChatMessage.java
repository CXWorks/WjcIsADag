package message;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author wjc
 *2015/10/24
 */

public class ChatMessage implements Serializable{

	String fromID;
	String toID;
	String message;
	Calendar time;

	public ChatMessage(String fromID, String toID, String message) {
		super();
		this.fromID = fromID;
		this.toID = toID;
		this.message = message;
		this.time = Calendar.getInstance();
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
