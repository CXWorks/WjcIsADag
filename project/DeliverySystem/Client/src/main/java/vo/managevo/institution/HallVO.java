package vo.managevo.institution;
import java.util.ArrayList;

import po.InfoEnum;
import po.companydata.HallPO;
import po.memberdata.StaffPO;
import vo.InfoVO;
import vo.managevo.staff.StaffVO;

public class HallVO extends InstitutionVO{
	
	public HallVO(String hallID, String city, String area,
			ArrayList<StaffVO> driver, ArrayList<StaffVO> deliver,
			ArrayList<StaffVO> counterman, String nearCenterID) {
		this();
		this.hallID = hallID;
		this.city = city;
		this.area = area;
		this.driver = driver;
		this.deliver = deliver;
		this.counterman = counterman;
		this.nearCenterID = nearCenterID;
	}
	
	private String hallID;
	private String city;
	private String area;
	private ArrayList<StaffVO> driver;
	private ArrayList<StaffVO> deliver;
	private ArrayList<StaffVO> counterman;
	private String nearCenterID;
	public HallVO(){
		super(InfoEnum.HALL);
	}
	//
	public HallVO(String hallID){
		this();
		this.hallID=hallID;
	}
	//
	public HallVO(HallPO po){
		this(po.getHallID(), po.getCity(), po.getArea(), null, null, null, po.getNearCenterID());
		this.setCounterman(selfDeepClonePOtoVO(po.getCounterman()));
		this.setDeliver(selfDeepClonePOtoVO(po.getDeliver()));
		this.setDriver(selfDeepClonePOtoVO(po.getDriver()));
	}
	//
	private void setDriver(ArrayList<StaffVO> driver) {
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
		ArrayList<StaffPO> driverPO=new ArrayList<StaffPO>(this.driver.size());
		ArrayList<StaffPO> deliverPO=new ArrayList<StaffPO>(deliver.size());
		ArrayList<StaffPO> countermanPO=new ArrayList<StaffPO>(this.counterman.size());
		//
		for (int i = 0; i < this.driver.size(); i++) {
			StaffPO temp=this.driver.get(i).toPO();
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
		return new HallPO(hallID, city, area, driverPO, deliverPO, countermanPO, nearCenterID);
	}
}
