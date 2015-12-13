package vo.managevo.staff;

import java.util.Calendar;

import po.InfoEnum;
import po.memberdata.DriverPO;
import po.memberdata.SexEnum;
import po.memberdata.StaffTypeEnum;
import userinfo.UserInfo;

/** 
 * Client//vo.managevo.staff//DriverVO.java
 * @author CXWorks
 * @date 2015年11月24日 下午12:54:58
 * @version 1.0 
 */
public class DriverVO extends StaffVO {
	private Calendar birth;
	private String tel;
	private Calendar licence_period;

	public DriverVO(){
		super(InfoEnum.DRIVER);
	}
	//
	/**
	 * @param staff
	 * @param iD
	 * @param name
	 * @param age
	 * @param personID
	 * @param sex
	 * @param love
	 * @param institutionID
	 * @param birth
	 * @param tel
	 * @param licence_period
	 */
	public DriverVO(StaffTypeEnum staff, String iD, String name, int age,
			String personID, SexEnum sex, String love, String institutionID,
			Calendar birth, String tel, Calendar licence_period) {
		super(staff, iD, name, age, personID, sex, love, institutionID);
		this.infoEnum=InfoEnum.DRIVER;
		this.birth = birth;
		this.tel = tel;
		this.licence_period = licence_period;
	}
	public DriverVO(DriverPO po){
		this(po.getStaff(), po.getID(), po.getName(), po.getAge(), po.getPersonID(), po.getSex(), po.getLove(), po.getInititutionID(), po.getBirth(), po.getTel(), po.getLicence_period());
	}
	//
	public DriverPO toPO(){
		DriverPO driverPO= new DriverPO(staff, ID, name, age, personID, sex, love, institutionID, birth, tel, licence_period);
		driverPO.setCache_OperatorID(UserInfo.getUserID());
		return driverPO;
	}
	public void setBirth(Calendar birth) {
		this.birth = birth;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setLicence_period(Calendar licence_period) {
		this.licence_period = licence_period;
	}
	public Calendar getBirth() {
		return birth;
	} 
	public String getTel() {
		return tel;
	}
	public Calendar getLicence_period() {
		return licence_period;
	}
}
