package po.transportdata;

import java.io.Serializable;
import java.util.ArrayList;

import po.FormPO;

public class LoadPO extends TransportPO  implements Serializable {

	String	peopletransport; //押运员
	String	expense;//运费

	public String getPeopletransport() {
		return peopletransport;
	}
	public String getExpense() {
		return expense;
	}

	
}
