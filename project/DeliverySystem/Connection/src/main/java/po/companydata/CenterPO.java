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
	private ArrayList<StaffPO> storeman;
	private ArrayList<StaffPO> counterman;

	public String getCenterID() {
		return centerID;
	}
	public String getCity() {
		return city;
	}
	public ArrayList<StaffPO> getStoreman() {
		return storeman;
	}
	public ArrayList<StaffPO> getCounterman() {
		return counterman;
	}
	public CenterPO(){
		super(InfoEnum.CENTER);
	}
	/**
	 * @param infoEnum
	 * @param centerID
	 * @param city
	 * @param storeman
	 * @param counterman
	 */
	public CenterPO(String centerID, String city,
			ArrayList<StaffPO> storeman, ArrayList<StaffPO> counterman) {
		this();
		this.centerID = centerID;
		this.city = city;
		this.storeman = storeman;
		this.counterman = counterman;
	}

}
