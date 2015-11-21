package po.transportdata;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import po.FormPO;

public abstract class TransportPO extends FormPO implements Serializable{
	private Calendar LoadDate;//装车日期
	private String  TransportID;//汽运编号、航班列次号 等
	private String	placeTo;//到达地
	private String	peopleSee;//监装员
	private String  expense;//运费
	private ArrayList<String> IDs;//本次装箱所有托运单号
	public Calendar getLoadDate() {
		return LoadDate;
	}
	public void setLoadDate(Calendar loadDate) {
		LoadDate = loadDate;
	}
	public String getTransportID() {
		return TransportID;
	}
	public void setTransportID(String transportID) {
		TransportID = transportID;
	}
	public String getPlaceTo() {
		return placeTo;
	}
	public void setPlaceTo(String placeTo) {
		this.placeTo = placeTo;
	}
	public String getPeopleSee() {
		return peopleSee;
	}
	public void setPeopleSee(String peopleSee) {
		this.peopleSee = peopleSee;
	}
	public String getExpense() {
		return expense;
	}
	public void setExpense(String expense) {
		this.expense = expense;
	}
	public ArrayList<String> getIDs() {
		return IDs;
	}
	public void setIDs(ArrayList<String> iDs) {
		IDs = iDs;
	}

}
