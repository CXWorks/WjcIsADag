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
}
