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
	public SalaryStrategyPO(){
		this.ID=ConfigurationEnum.SALARY_STRATEGY;
		this.staff=StaffTypeEnum.ADMINISTRATOR;
		this.base=1000;
		this.commission=100;
		this.bonus=1000;
	}
	public int getBase() {
		return base;
	}
	public int getCommission() {
		return commission;
	}
	public int getBonus() {
		return bonus;
	}
	
}
