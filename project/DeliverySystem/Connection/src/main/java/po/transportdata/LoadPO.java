package po.transportdata;

import java.io.Serializable;
import java.util.ArrayList;

import po.FormPO;

public class LoadPO extends TransportPO  implements Serializable {

	String	truckID;
	String	peopletransport;
	String	expense;
	public String getTruckID() {
		return truckID;
	}
	public String getPeopletransport() {
		return peopletransport;
	}
	public String getExpense() {
		return expense;
	}

	
}
