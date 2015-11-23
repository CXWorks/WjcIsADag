package po.memberdata;

import java.io.Serializable;

import po.CommonPO;
import po.InfoEnum;
import po.InfoPO;
import po.transportdata.TransportationEnum;

public class StaffPO extends InfoPO implements Serializable{
	private StaffTypeEnum staff;
	private String ID;
	private String name;
	private int age;
	private String personID;
	private SexEnum sex;
	private String love;
	private String inititutionID;
	public StaffPO(){
		super(InfoEnum.STAFF);
	}
	//
	
	public StaffTypeEnum getStaff() {
		return staff;
	}
	public String getInititutionID() {
		return inititutionID;
	}

	public String getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public String getPersonID() {
		return personID;
	}
	public SexEnum getSex() {
		return sex;
	}
	public String getLove() {
		return love;
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
	/**
	 * @param staff
	 * @param iD
	 * @param name
	 * @param age
	 * @param personID
	 * @param sex
	 * @param love
	 */
	public StaffPO(StaffTypeEnum staff, String iD, String name, int age,
			String personID, SexEnum sex, String love,String institutionID) {
		this();
		this.staff = staff;
		ID = iD;
		this.name = name;
		this.age = age;
		this.personID = personID;
		this.sex = sex;
		this.love = love;
		this.inititutionID=institutionID;
	}
	
	public StaffPO(String staff, String iD, String name, int age,
			String personID, String sex, String love,String institutionID) {
		this();
		this.setStaff(staff);
		ID = iD;
		this.name = name;
		this.age = age;
		this.personID = personID;
		this.setSex(sex);
		this.love = love;
		this.inititutionID=institutionID;
	}
}
