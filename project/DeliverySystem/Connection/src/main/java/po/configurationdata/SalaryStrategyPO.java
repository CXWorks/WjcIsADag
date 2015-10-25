package po.configurationdata;

import java.io.Serializable;

import po.configurationdata.enums.ConfigurationEnum;
import po.memberdata.StaffTypeEnum;

public class SalaryStrategyPO implements Serializable{
	private ConfigurationEnum ID;
	private StaffTypeEnum staff;
	private int base;
	private int commission;
	private int bonus;
}
