package vo.managevo.institution;
import java.util.ArrayList;

import po.InfoEnum;
import po.memberdata.StaffPO;
import vo.InfoVO;


public class CenterVO extends InfoVO{
	
	public CenterVO(){
		super(InfoEnum.CENTER);
	}
	
	public CenterVO(String centerID, String city, ArrayList<StaffPO> storeman,
			ArrayList<StaffPO> counterman) {
		this();
		this.centerID = centerID;
		this.city = city;
		this.storeman = storeman;
		this.counterman = counterman;
	}

	private String centerID;
	private String city;
	private ArrayList<StaffPO> storeman;
	private ArrayList<StaffPO> counterman;
	
}
