package po.companydata;
import java.io.Serializable;
import java.util.ArrayList;

import javax.sound.midi.MidiDevice.Info;

import po.CommonPO;
import po.InfoEnum;
import po.InfoPO;
import po.memberdata.DriverPO;
import po.memberdata.StaffPO;
import util.DataType;

public class HallPO extends InfoPO implements Serializable{
	private String hallID;
	private String city;
	private String area;
	private ArrayList<DriverPO> driver;
	private ArrayList<StaffPO> deliver;
	private ArrayList<StaffPO> counterman;
	private String nearCenterID;
	public String getHallID() {
		return hallID;
	}
	public String getCity() {
		return city;
	}
	public String getArea() {
		return area;
	}
	public ArrayList<DriverPO> getDriver() {
		return driver;
	}
	public ArrayList<StaffPO> getDeliver() {
		return deliver;
	}
	public ArrayList<StaffPO> getCounterman() {
		return counterman;
	}
	public String getNearCenterID() {
		return nearCenterID;
	}
	/**
	 * @param infoEnum
	 * @param hallID
	 * @param city
	 * @param area
	 * @param driver2
	 * @param deliver
	 * @param counterman
	 * @param nearCenterID
	 */
	public HallPO(String hallID, String city, String area,
			ArrayList<DriverPO> driver2, ArrayList<StaffPO> deliver,
			ArrayList<StaffPO> counterman, String nearCenterID) {
		super(InfoEnum.HALL);
		this.hallID = hallID;
		this.city = city;
		this.area = area;
		this.driver = driver2;
		this.deliver = deliver;
		this.counterman = counterman;
		this.nearCenterID = nearCenterID;
	}
	
}
