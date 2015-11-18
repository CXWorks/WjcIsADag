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
	
	
}
