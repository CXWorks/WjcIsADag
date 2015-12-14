package po.companydata;
import java.io.Serializable;
import java.util.ArrayList;

import javax.sound.midi.MidiDevice.Info;

import po.CommonPO;
import po.InfoEnum;
import po.InfoPO;
import po.memberdata.StaffPO;
import util.DataType;


public class CenterPO extends InfoPO implements Serializable{
	private String centerID;
	private String city;

	public String getCenterID() {
		return centerID;
	}
	public String getCity() {
		return city;
	}
	public CenterPO(){
		super(InfoEnum.CENTER);
	}
	/**
	 * @param infoEnum
	 * @param centerID
	 * @param city
	 */
	public CenterPO(String centerID, String city) {
		this();
		this.centerID = centerID;
		this.city = city;
	}

}
