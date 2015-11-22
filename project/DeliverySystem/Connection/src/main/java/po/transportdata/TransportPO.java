package po.transportdata;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import po.FormPO;

public abstract class TransportPO extends FormPO implements Serializable {

	protected Calendar LoadDate;// 装车日期
	protected String TransportID;// 汽运编号、航班列次号 等
	protected String placeTo;// 到达地
	protected String peopleSee;// 监装员
	protected String expense;// 运费
	protected ArrayList<String> IDs;// 本次装箱所有托运单号

	public TransportPO(){
		super();
	}
	
	public Calendar getLoadDate() {
		return LoadDate;
	}

	public Timestamp getLoadDateForSQL(){
		return new Timestamp(this.LoadDate.getTimeInMillis());
	}
	
	public String getTransportID() {
		return TransportID;
	}

	public String getPlaceTo() {
		return placeTo;
	}

	public String getPeopleSee() {
		return peopleSee;
	}

	public String getExpense() {
		return expense;
	}

	public ArrayList<String> getIDs() {
		return IDs;
	}

}
