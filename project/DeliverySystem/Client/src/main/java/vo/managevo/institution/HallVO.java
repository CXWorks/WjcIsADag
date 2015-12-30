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
	
	public HallVO(String hallID,String city,String area,String nearCenterID,String cityName){
		this();
		this.institutionID=hallID;
		this.cityID=city;
		this.area=area;
		this.nearCenterID=nearCenterID;
		this.cityName=cityName;
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
		this(po.getHallID(), po.getCityID(), po.getArea(), po.getNearCenterID(),po.getCityName());
	}
	//toPO
	public HallPO toPO(){
		HallPO hallPO= new HallPO(institutionID, cityID, area, nearCenterID,cityName);
		hallPO.setCache_OperatorID(UserInfo.getUserID());
		return hallPO;
	}
	//
	public String getHallID() {
		return institutionID;
	}
	public String getCityID() {
		return cityID;
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
