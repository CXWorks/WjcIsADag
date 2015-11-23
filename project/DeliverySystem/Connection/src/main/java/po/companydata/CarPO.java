package po.companydata;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.sound.midi.MidiDevice.Info;
import javax.swing.ImageIcon;

import po.CommonPO;
import po.InfoEnum;
import po.InfoPO;
import util.DataType;

public class CarPO extends InfoPO implements Serializable{
	private boolean free;
	private int carID;
	private Calendar useTime;
	private ImageIcon img;
	//以下为无用内容，67脑洞真大
	private int engineID;
	private int nameID;
	private int chassisID;//chassis是车辆底盘的意思
	private Calendar buyTime;
	//
	public boolean isFree() {
		return free;
	}
	public int getCarID() {
		return carID;
	}
	public Calendar getUseTime() {
		return useTime;
	}
	public ImageIcon getImg() {
		return img;
	}
	public int getEngineID() {
		return engineID;
	}
	public int getNameID() {
		return nameID;
	}
	public int getChassisID() {
		return chassisID;
	}
	public Calendar getBuyTime() {
		return buyTime;
	}
	public Timestamp getBuyTimeForSQL() {
		return new Timestamp(this.buyTime.getTimeInMillis());
	}
	//
	public CarPO(){
		super(InfoEnum.CAR);
	}
	/**
	 * @param infoEnum
	 * @param free
	 * @param carID
	 * @param useTime
	 * @param img
	 * @param engineID
	 * @param nameID
	 * @param chassisID
	 * @param buyTime
	 */
	public CarPO(boolean free, int carID, Calendar useTime,
			ImageIcon img, int engineID, int nameID, int chassisID,
			Calendar buyTime) {
		this();
		this.free = free;
		this.carID = carID;
		this.useTime = useTime;
		this.img = img;
		this.engineID = engineID;
		this.nameID = nameID;
		this.chassisID = chassisID;
		this.buyTime = buyTime;
	}
	
	public CarPO(boolean free, int carID, Timestamp useTime,
			ImageIcon img, int engineID, int nameID, int chassisID,
			Timestamp buyTime) {
		this();
		this.free = free;
		this.carID = carID;
		Calendar temp = Calendar.getInstance();
		temp.setTime(useTime);
		this.useTime = temp;
		this.img = img;
		this.engineID = engineID;
		this.nameID = nameID;
		this.chassisID = chassisID;
		temp.setTime(buyTime);
		this.buyTime = temp;
	}
	
}
