package po.systemdata;

import java.io.Serializable;
import java.util.Calendar;

import po.InfoEnum;
import po.InfoPO;

public class LogPO extends InfoPO implements Serializable{

	private String personID;
	private Calendar time;
	private String info;
	
	protected LogPO(InfoEnum infoEnum) {
		super(infoEnum);
		// TODO Auto-generated constructor stub
	}

	public LogPO(InfoEnum infoEnum, String personID, Calendar time, String info) {
		this(InfoEnum.LOG);
		this.personID = personID;
		this.time = time;
		this.info = info;
	}
	

}
