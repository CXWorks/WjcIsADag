package vo.managevo.staff;

import po.InfoEnum;
import po.memberdata.SexEnum;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import userinfo.UserInfo;
import vo.InfoVO;
import vo.managevo.institution.CenterVO;
import vo.managevo.institution.InstitutionVO;

public class StaffVO extends InfoVO{
	
	public StaffVO(){
		super(InfoEnum.STAFF);
	}
	public StaffVO(StaffTypeEnum staff, String iD, String name, int age,
			String personID, SexEnum sex, String love,String institutionID) {
		this();
		this.staff = staff;
		ID = iD;
		this.name = name;
		this.age = age;
		this.personID = personID;
		this.sex = sex;
		this.love = love;
		this.institutionID=institutionID;
	}
	
	protected StaffTypeEnum staff;
	protected String ID;//员工编号
	protected String name;
	protected int age;
	protected String personID;//身份证号
	protected SexEnum sex;
	protected String love;
	protected String institutionID;
	//
	public StaffVO(StaffPO po){
		this(po.getStaff(), po.getID(), po.getName(), po.getAge(), po.getPersonID(), po.getSex(), po.getLove(),po.getInititutionID());
	}
	protected StaffVO(InfoEnum infoEnum) {
		super(infoEnum);
		
	}
	public StaffPO toPO(){
		StaffPO staffPO= new StaffPO(staff, ID, name, age, personID, sex, love,institutionID);
		staffPO.setCache_OperatorID(UserInfo.getUserID());
		return staffPO;
	}
	public StaffTypeEnum getStaff() {
		return staff;
	}
	public String getID(){
		return ID;
	}
	public String getName(){
		return name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPersonID() {
		return personID;
	}
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	public SexEnum getSex() {
		return sex;
	}
	public void setSex(SexEnum sex) {
		this.sex = sex;
	}
	public String getLove() {
		return love;
	}
	public void setLove(String love) {
		this.love = love;
	}
	public String getInstitutionID() {
		return institutionID;
	}
	public void setInstitutionID(String institutionID) {
		this.institutionID = institutionID;
	}
	public void setStaff(StaffTypeEnum staff) {
		this.staff = staff;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public void setStaff(String staff) {
		switch (staff) {
		case "ADMINISTRATOR":
			this.staff = StaffTypeEnum.ADMINISTRATOR;
			break;
		case "BURSAR":
			this.staff = StaffTypeEnum.BURSAR;
			break;
		case "CENTER_COUNTERMAN":
			this.staff = StaffTypeEnum.CENTER_COUNTERMAN;
			break;
		case "DELIVER":
			this.staff = StaffTypeEnum.DELIVER;
			break;
		case "HALL_COUNTERMAN":
			this.staff = StaffTypeEnum.HALL_COUNTERMAN;
			break;
		case "MANAGER":
			this.staff = StaffTypeEnum.MANAGER;
		case "STOREMAN":
			this.staff = StaffTypeEnum.STOREMAN;
			break;
		}
	}
	public void setSex(String sex) {
		switch (sex) {
		case "MAN":
			this.sex = SexEnum.MAN;
			break;
		case "WOMAN":
			this.sex = SexEnum.WOMAN;
			break;
		case "OTHERS":
			this.sex = SexEnum.OTHERS;
			break;
		}
	}
	
}

