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
	private String cityID;
	private String cityName;
	private String area;
	private String nearCenterID;
	public String getHallID() {
		return hallID;
	}
	public String getCityID() {
		return cityID;
	}
	public String getCityName(){
		return cityName;
	}
	public String getArea() {
		return area;
	}
	public String getNearCenterID() {
		return nearCenterID;
	}
	/**
	 * @param infoEnum
	 * @param hallID
	 * @param city
	 * @param area
	 * @param nearCenterID
	 */
	public HallPO(String hallID, String cityID, String area,
			String nearCenterID,String cityName) {
		super(InfoEnum.HALL);
		this.hallID = hallID;
		this.cityID = cityID;
		this.area = area;
		this.nearCenterID = nearCenterID;
		this.cityName=cityName;
	}
	
}
