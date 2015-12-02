package vo.managevo.staff;

import po.InfoEnum;
import po.memberdata.SexEnum;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import vo.InfoVO;

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
	protected String ID;
	protected String name;
	protected int age;
	protected String personID;
	protected SexEnum sex;
	protected String love;
	protected String institutionID;
	//
	public StaffVO(StaffPO po){
		this(po.getStaff(), po.getID(), po.getName(), po.getAge(), po.getPersonID(), po.getSex(), po.getLove(),po.getInititutionID());
	}
	public StaffPO toPO(){
		return new StaffPO(staff, ID, name, age, personID, sex, love,institutionID);
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
	
}

