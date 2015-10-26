package po.transportdata;

import java.io.Serializable;
import java.util.ArrayList;

import po.FormPO;

public abstract class TransportPO extends FormPO implements Serializable{
	String	LoadDate;
	String	LoadID;
	String	placeTo;
	String	peopleSee;
	ArrayList<String>	IDs;
}
