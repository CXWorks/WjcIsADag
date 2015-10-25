package po.companydata;
import java.io.Serializable;
import java.util.ArrayList;

import po.memberdata.StaffPO;


public class CenterPO implements Serializable{
	private String centerID;
	private String city;
	private ArrayList<StaffPO> storeman;
	private ArrayList<StaffPO> counterman;
	
	
}
