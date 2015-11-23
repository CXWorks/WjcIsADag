package po.transportdata;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import po.FormEnum;
import po.FormPO;

public class LoadPO extends TransportPO implements Serializable {

	String peopleTransport; // 押运员

	public LoadPO(String formID,String peopleTransport, Calendar LoadDate,
			String TransportID, String placeTo, String peopleSee,
			String expense, ArrayList<String> IDs) {
		super(FormEnum.TRANSPORT_HALL,formID);

		this.peopleTransport = peopleTransport;
		this.LoadDate = LoadDate;
		this.TransportID = TransportID;
		this.placeTo = placeTo;
		this.peopleSee = peopleSee;
		this.expense = expense;
		this.IDs = IDs;
		this.formType = FormEnum.TRANSPORT_HALL;
	}
	
	public LoadPO(String formID,String peopleTransport, Timestamp LoadDate,
			String TransportID, String placeTo, String peopleSee,
			String expense, ArrayList<String> IDs) {
		super(FormEnum.TRANSPORT_HALL,formID);

		this.peopleTransport = peopleTransport;
		Calendar temp=Calendar.getInstance();
		temp.setTime(LoadDate);
		this.LoadDate = temp;
		this.TransportID = TransportID;
		this.placeTo = placeTo;
		this.peopleSee = peopleSee;
		this.expense = expense;
		this.IDs = IDs;
		this.formType = FormEnum.TRANSPORT_HALL;
	}
	
	public String getPeopleTransport() {
		return peopleTransport;
	}

	public String getExpense() {
		return expense;
	}
}
