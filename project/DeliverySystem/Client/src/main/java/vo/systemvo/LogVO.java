package vo.systemvo;

import java.io.Serializable;
import java.util.Calendar;

import po.InfoEnum;
import po.systemdata.LogPO;
import vo.InfoVO;

public class LogVO extends InfoVO implements Serializable{

	public String personID;
	public Calendar time;
	public String info;
	
	protected LogVO(InfoEnum infoEnum) {
		super(infoEnum);
	}

	public LogVO( String personID, Calendar time, String info) {
		this(InfoEnum.LOG);
		this.personID = personID;
		this.time = time;
		this.info = info;
	}
	public LogVO (LogPO po){
		this( po.getPersonID(), po.getTime(), po.getInfo());
	}
	public LogPO toPO(){
		return new LogPO( personID, time, info);
	}
	//
	public boolean fuzzyCheck(String key){
		if (personID.contains(key)) {
			return true;
		}
		if (info.contains(key)) {
			return true;
		}
		if (time.toString().contains(key)) {
			return true;
		}
		return false;
	}
	@Override
	public String toString(){
		return this.time.getTime().toString()+" "+personID+" "+info;
	}
}
