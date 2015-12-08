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

	public Calendar getTime() {
		return time;
	}

	public void setFromID(String fromID) {
		this.fromID = fromID;
	}

	public void setToID(String toID) {
		this.toID = toID;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}


}
