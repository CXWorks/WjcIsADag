package po.transportdata;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import po.FormPO;

public abstract class TransportPO extends FormPO implements Serializable{
	Timestamp	LoadDate;
	String	LoadID;
	String	placeTo;
	String	peopleSee;
	ArrayList<String>	IDs;
	public Timestamp getLoadDate() {
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
	
	public void setData(Timestamp LoadDate) {
		this.LoadDate = LoadDate;
	}
}
