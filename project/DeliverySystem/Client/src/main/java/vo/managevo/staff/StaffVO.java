package vo.managevo.staff;

import po.memberdata.SexEnum;
import po.memberdata.StaffTypeEnum;
import vo.InfoVO;

public class StaffVO extends InfoVO{
	
	public StaffVO(){
		
	}
	public StaffVO(StaffTypeEnum staff, String iD, String name, int age,
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
	
	private StaffTypeEnum staff;
	private String ID;
	private String name;
	private int age;
	private String personID;
	private SexEnum sex;
	private String love;
}
