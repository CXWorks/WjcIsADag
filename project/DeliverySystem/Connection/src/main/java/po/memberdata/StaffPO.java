package po.memberdata;

import java.io.Serializable;

import po.CommonPO;

public class StaffPO extends CommonPO implements Serializable{
	private StaffTypeEnum staff;
	private String ID;
	private String name;
	private int age;
	private String personID;
	private SexEnum sex;
	private String love;
}
