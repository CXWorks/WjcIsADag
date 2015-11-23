package po.configurationdata;

import java.io.Serializable;

import po.CommonPO;
import po.InfoEnum;
import po.InfoPO;
import po.memberdata.StaffTypeEnum;

public class SalaryStrategyPO extends InfoPO implements Serializable{
	private StaffTypeEnum staff;
	private int base;
	private int commission;
	private int bonus;
	public SalaryStrategyPO(int base,int commission,int bonus,StaffTypeEnum staff){
		super(InfoEnum.SALARY);
		this.base=base;
		this.commission=commission;
		this.bonus=bonus;
		this.staff=staff;
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
