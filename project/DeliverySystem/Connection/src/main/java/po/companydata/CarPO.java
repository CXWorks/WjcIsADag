package po.companydata;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.ImageIcon;

import po.CommonPO;
import po.InfoEnum;
import po.InfoPO;
import util.DataType;

public class CarPO extends InfoPO implements Serializable{
	private boolean free;
	private String carID;
	private Calendar useTime;
	private ImageIcon img;
	//以下为无用内容，67脑洞真大
	private String engineID;
	private String nameID;
	private String chassisID;//chassis是车辆底盘的意思
	private Calendar buyTime;
	//
	public boolean isFree() {
		return free;
	}
	
	public Calendar getUseTime() {
		return useTime;
	}
	public Timestamp getUseTimeForSQL() {
		return new Timestamp(this.useTime.getTimeInMillis());
	}
	public ImageIcon getImg() {
		return img;
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

	public String getCarID() {
		return carID;
	}

	public String getEngineID() {
		return engineID;
	}

	public String getNameID() {
		return nameID;
	}

	public String getChassisID() {
		return chassisID;
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
	public CarPO(boolean free, String carID,
			Calendar useTime, ImageIcon img, String engineID, String nameID,
			String chassisID, Calendar buyTime) {
		super(InfoEnum.CAR);
		this.free = free;
		this.carID = carID;
		this.useTime = useTime;
		this.img = img;
		this.engineID = engineID;
		this.nameID = nameID;
		this.chassisID = chassisID;
		this.buyTime = buyTime;
	}
	public CarPO(boolean free, String carID,
			Timestamp useTime, ImageIcon img, String engineID, String nameID,
			String chassisID, Timestamp buyTime) {
		super(InfoEnum.CAR);
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
