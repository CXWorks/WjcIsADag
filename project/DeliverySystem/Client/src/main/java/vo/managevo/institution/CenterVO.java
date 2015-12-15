package vo.managevo.institution;
import java.util.ArrayList;

import po.InfoEnum;
import po.companydata.CenterPO;
import po.memberdata.StaffPO;
import userinfo.UserInfo;
import vo.InfoVO;
import vo.managevo.staff.StaffVO;


public class CenterVO extends InstitutionVO{
	
	public CenterVO(){
		super(InfoEnum.CENTER);
	}
	
	public CenterVO(String centerID,String city){
		this();
		this.institutionID=centerID;
		this.city=city;
	}
	//
	public CenterVO(CenterPO po){
		this(po.getCenterID(), po.getCity());
	}
	//

	

	
	public CenterPO toPO(){
		
		CenterPO centerPO= new CenterPO(institutionID, city);
		centerPO.setCache_OperatorID(UserInfo.getUserID());
		return centerPO;
	}

	public String getCenterID() {
		return institutionID;
	}

	public String getCity() {
		return city;
	}

	@Override
	public int getStaffCount() {
		return 0;
	}
}
