package vo.systemvo;

import java.io.Serializable;
import java.util.Calendar;

import po.InfoEnum;
import vo.InfoVO;

public class LogVO extends InfoVO implements Serializable{

	private String personID;
	private Calendar time;
	private String info;
	
	protected LogVO(InfoEnum infoEnum) {
		super(infoEnum);
		// TODO Auto-generated constructor stub
	}

	public LogVO(InfoEnum infoEnum, String personID, Calendar time, String info) {
		this(InfoEnum.LOG);
		this.personID = personID;
		this.time = time;
		this.info = info;
	}
	

}
