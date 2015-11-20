package po.transportdata;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import po.FormPO;

public abstract class TransportPO extends FormPO implements Serializable{
	Calendar	LoadDate;
	String	LoadID;
	String	placeTo;
	String	peopleSee;
	ArrayList<String>	IDs;
	
	public Calendar getLoadDate() {
		return LoadDate;
	}
	public String getLoadID() {
		return LoadID;
	}
	public String getPlaceTo() {
		return placeTo;
	}
	public String getPeopleSee() {
		return peopleSee;
	}
	public ArrayList<String> getIDs() {
		return IDs;
	}
}
