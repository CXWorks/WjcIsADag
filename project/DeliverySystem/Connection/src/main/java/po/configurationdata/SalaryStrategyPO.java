package po.configurationdata;

import java.io.Serializable;

import po.CommonPO;
import po.InfoPO;
import po.memberdata.StaffTypeEnum;

public class SalaryStrategyPO extends InfoPO implements Serializable{
	private StaffTypeEnum staff;
	private int base;
	private int commission;
	private int bonus;
	public SalaryStrategyPO(){
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
