package vo.managevo.institution;
import java.util.ArrayList;

import po.InfoEnum;
import po.companydata.CenterPO;
import po.memberdata.StaffPO;
import vo.InfoVO;
import vo.managevo.staff.StaffVO;


public class CenterVO extends InstitutionVO{
	
	public CenterVO(){
		super(InfoEnum.CENTER);
	}
	
	public CenterVO(String centerID, String city, ArrayList<StaffVO> storeman,
			ArrayList<StaffVO> counterman) {
		this();
		this.centerID = centerID;
		this.city = city;
		this.storeman = storeman;
		this.counterman = counterman;
	}

	private String centerID;
	private String city;
	private ArrayList<StaffVO> storeman;
	private ArrayList<StaffVO> counterman;
	//
	public CenterVO(CenterPO po){
		this(po.getCenterID(), po.getCity(), null, null);
		this.setStoreman(selfDeepClonePOtoVO(po.getStoreman()));
		this.setCounterman(selfDeepClonePOtoVO(po.getCounterman()));
	}
	//

	private void setStoreman(ArrayList<StaffVO> storeman) {
		this.storeman = storeman;
	}

	private void setCounterman(ArrayList<StaffVO> counterman) {
		this.counterman = counterman;
	}
	
}
