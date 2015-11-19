package po.transportdata;

import java.io.Serializable;
import java.util.ArrayList;

import po.FormPO;

public class CenterOutPO extends TransportPO implements Serializable{

	String	placeFrom;
	String	TransID;
	String	shelfNum;
	public String getPlaceFrom() {
		return placeFrom;
	}
	public String getTransID() {
		return TransID;
	}
	public String getShelfNum() {
		return shelfNum;
	}
	
	
}
