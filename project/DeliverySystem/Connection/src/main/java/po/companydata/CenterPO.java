package po.companydata;
import java.io.Serializable;
import java.util.ArrayList;

import javax.sound.midi.MidiDevice.Info;

import po.CommonPO;
import po.InfoPO;
import po.memberdata.StaffPO;


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
	
	
}
