package po.configurationdata;

import java.io.Serializable;

import po.CommonPO;
import po.InfoEnum;
import po.InfoPO;
import po.memberdata.StaffTypeEnum;

public class SalaryStrategyPO extends InfoPO implements Serializable {
	private StaffTypeEnum staff;
	private int base;
	private int commission;
	private int bonus;

	public SalaryStrategyPO(int base, int commission, int bonus,
			StaffTypeEnum staff) {
		super(InfoEnum.SALARY);
		this.base = base;
		this.commission = commission;
		this.bonus = bonus;
		this.staff = staff;
	}

	public SalaryStrategyPO(int base, int commission, int bonus,
			String staff) {
		super(InfoEnum.SALARY);
		this.base = base;
		this.commission = commission;
		this.bonus = bonus;
		this.setStaff(staff);
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
		case "DRIVER":
			this.staff = StaffTypeEnum.DRIVER;
			break;
		}
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

	public StaffTypeEnum getStaff() {
		return staff;
	}

}
