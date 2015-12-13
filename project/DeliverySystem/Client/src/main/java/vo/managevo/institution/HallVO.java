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
	
	public HallVO(String hallID, String city, String area,
			ArrayList<DriverVO> driver, ArrayList<StaffVO> deliver,
			ArrayList<StaffVO> counterman, String nearCenterID) {
		this();
		this.institutionID = hallID;
		this.city = city;
		this.area = area;
		this.driver = driver;
		this.deliver = deliver;
		this.counterman = counterman;
		this.nearCenterID = nearCenterID;
	}
	public HallVO(String hallID,String city,String area,String nearCenterID){
		this();
		this.institutionID=hallID;
		this.city=city;
		this.area=area;
		this.nearCenterID=nearCenterID;
		this.deliver=new ArrayList<StaffVO>();
		this.driver=new ArrayList<DriverVO>();
		this.counterman=new ArrayList<StaffVO>();
	}
	
	private String area;
	private ArrayList<DriverVO> driver;
	private ArrayList<StaffVO> deliver;
	private ArrayList<StaffVO> counterman;
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
		this(po.getHallID(), po.getCity(), po.getArea(), null, null, null, po.getNearCenterID());
		this.setCounterman(selfDeepClonePOtoVO(po.getCounterman()));
		this.setDeliver(selfDeepClonePOtoVO(po.getDeliver()));
		//
		ArrayList<DriverPO> driverPO=po.getDriver();
		ArrayList<DriverVO> driverVO=new ArrayList<DriverVO>(driverPO.size());
		for (int i = 0; i < driverPO.size(); i++) {
			DriverPO each=driverPO.get(i);
			DriverVO temp=new DriverVO(each);
			driverVO.add(temp);
		}
		this.setDriver(driverVO);
	}
	//
	private void setDriver(ArrayList<DriverVO> driver) {
		this.driver = driver;
	}
	private void setDeliver(ArrayList<StaffVO> deliver) {
		this.deliver = deliver;
	}
	private void setCounterman(ArrayList<StaffVO> counterman) {
		this.counterman = counterman;
	}
	//toPO
	public HallPO toPO(){
		ArrayList<DriverPO> driverPO=new ArrayList<DriverPO>(this.driver.size());
		ArrayList<StaffPO> deliverPO=new ArrayList<StaffPO>(deliver.size());
		ArrayList<StaffPO> countermanPO=new ArrayList<StaffPO>(this.counterman.size());
		//
		for (int i = 0; i < this.driver.size(); i++) {
			DriverPO temp=this.driver.get(i).toPO();
			driverPO.add(temp);
		}
		for (int i = 0; i < this.deliver.size(); i++) {
			StaffPO temp=this.deliver.get(i).toPO();
			deliverPO.add(temp);
		}
		for (int i = 0; i < this.counterman.size(); i++) {
			StaffPO temp=this.counterman.get(i).toPO();
			countermanPO.add(temp);
		}
		//
		HallPO hallPO= new HallPO(institutionID, city, area, driverPO, deliverPO, countermanPO, nearCenterID);
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
		return driver.size() + deliver.size() + counterman.size();
	}
}
