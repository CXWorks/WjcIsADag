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
	
	public CenterVO(String centerID,String cityID,String cityName){
		this();
		this.institutionID=centerID;
		this.cityID=cityID;
		this.cityName=cityName;
	}
	//
	public CenterVO(CenterPO po){
		this(po.getCenterID(), po.getCityID(),po.getCityName());
	}
	//

	

	
	public CenterPO toPO(){
		
		CenterPO centerPO= new CenterPO(institutionID, cityID,cityName);
		centerPO.setCache_OperatorID(UserInfo.getUserID());
		return centerPO;
	}

	public String getCenterID() {
		return institutionID;
	}

	public String getCityID() {
		return cityID;
	}

	@Override
	public int getStaffCount() {
		return 0;
	}
}
