package po.memberdata;

import java.io.Serializable;

import po.CommonPO;
import po.InfoPO;

public class StaffPO extends InfoPO implements Serializable{
	private StaffTypeEnum staff;
	private String ID;
	private String name;
	private int age;
	private String personID;
	private SexEnum sex;
	private String love;
	//
	public StaffTypeEnum getStaff() {
		return staff;
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
			String personID, SexEnum sex, String love) {
		super();
		this.staff = staff;
		ID = iD;
		this.name = name;
		this.age = age;
		this.personID = personID;
		this.sex = sex;
		this.love = love;
	}
}
