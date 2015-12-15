package po.memberdata;

import java.sql.Timestamp;
import java.util.Calendar;

import po.InfoEnum;

public class DriverPO extends StaffPO {

	private Calendar birth;
	private String tel;
	private Calendar licence_period;

	public Calendar getBirth() {
		return birth;
	}

	public String getTel() {
		return tel;
	}

	public Calendar getLicence_period() {
		return licence_period;
	}

	public Timestamp getBirthForSQL() {
		return new Timestamp(this.birth.getTimeInMillis());
	}

	public Timestamp getLicence_periodForSQL() {
		return new Timestamp(this.licence_period.getTimeInMillis());
	}

	public DriverPO(Timestamp birth, String tel, Timestamp licence_period,
			String iD, String name, int age, String personID, String sex,
			String love,String institutionID) {
		super("DRIVER", iD, name, age, personID, sex, love,institutionID);
		Calendar temp = Calendar.getInstance();
		temp.setTime(birth);
		this.birth = temp;
		this.tel = tel;
		temp.setTime(licence_period);
		this.licence_period = temp;
		this.infoEnum=InfoEnum.DRIVER;
	}

	/**
	 * @param staff
	 * @param iD
	 * @param name
	 * @param age
	 * @param personID
	 * @param sex
	 * @param love
	 * @param institutionID
	 * @param birth
	 * @param tel
	 * @param licence_period
	 */
	public DriverPO(StaffTypeEnum staff, String iD, String name, int age,
			String personID, SexEnum sex, String love, String institutionID,
			Calendar birth, String tel, Calendar licence_period) {
		super(staff, iD, name, age, personID, sex, love, institutionID);
		this.infoEnum=InfoEnum.DRIVER;
		this.birth = birth;
		this.tel = tel;
		this.licence_period = licence_period;
	}



}
