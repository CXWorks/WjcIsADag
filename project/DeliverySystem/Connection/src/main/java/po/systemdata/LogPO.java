package po.systemdata;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import po.InfoEnum;
import po.InfoPO;

public class LogPO extends InfoPO implements Serializable{

	private String personID;
	private Calendar time;
	private String info;
	
	protected LogPO(InfoEnum infoEnum) {
		super(infoEnum);
	}

	public LogPO( String personID, Calendar time, String info) {
		this(InfoEnum.LOG);
		this.personID = personID;
		this.time = time;
		this.info = info;
	}
	public LogPO( String personID, Timestamp time, String info) {
		this(InfoEnum.LOG);
		this.personID = personID;
		Calendar temp = Calendar.getInstance();
		temp.setTime(time);
		this.time = temp;
		this.info = info;
	}

	public String getPersonID() {
		return personID;
	}

	public Calendar getTime() {
		return time;
	}
	public Timestamp getTimeForSQL() {
		return new Timestamp(this.time.getTimeInMillis());
	}
	public String getInfo() {
		return info;
	}
	

}
