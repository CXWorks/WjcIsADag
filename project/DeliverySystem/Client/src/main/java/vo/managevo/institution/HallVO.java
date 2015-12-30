package vo.managevo.institution;
import java.util.ArrayList;

import po.InfoEnum;
import po.companydata.HallPO;
import po.memberdata.DriverPO;
import po.memberdata.StaffPO;
import userinfo.UserInfo;
import vo.InfoVO;
import vo.managevo.staff.DriverVO;
import vo.managevo.staff.StaffVO;

public class HallVO extends InstitutionVO{
	
	public HallVO(String hallID,String city,String area,String nearCenterID){
		this();
		this.institutionID=hallID;
		this.city=city;
		this.area=area;
		this.nearCenterID=nearCenterID;
	}
	
	private String area;

	public String getNearCenterID() {
		return nearCenterID;
	}

	public void setNearCenterID(String nearCenterID) {
		this.nearCenterID = nearCenterID;
	}

	private String nearCenterID;
	public HallVO(){
		super(InfoEnum.HALL);
	}
	//
	public HallVO(String hallID){
		this();
		this.institutionID=hallID;
	}
	//
	public HallVO(HallPO po){
		this(po.getHallID(), po.getCity(), po.getArea(), po.getNearCenterID());
	}
	//toPO
	public HallPO toPO(){
		HallPO hallPO= new HallPO(institutionID, city, area, nearCenterID);
		hallPO.setCache_OperatorID(UserInfo.getUserID());
		return hallPO;
	}
	//
	public String getHallID() {
		return institutionID;
	}
	public String getCity() {
		return city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public int getStaffCount() {
		return 0;
	}
}
