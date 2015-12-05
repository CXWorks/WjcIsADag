package vo.managevo.car;

import java.util.Calendar;

import javax.swing.ImageIcon;

import po.InfoEnum;
import po.companydata.CarPO;
import vo.InfoVO;

public class CarVO extends InfoVO{
	private boolean free;
	private String carID;    //车辆编号
	private Calendar useTime; //服役时间
	private ImageIcon imag;
	//以下为无用内容
	private String engineID;
	private String nameID;      //车牌号
	private String chassisID;//chassis是车辆底盘的意思
	private Calendar buyTime;
	//

	public CarVO(){
		super(InfoEnum.CAR);
	}


	/**
	 * @param free
	 * @param carID
	 * @param useTime
	 * @param imag
	 * @param engineID
	 * @param nameID
	 * @param chassisID
	 * @param buyTime
	 */
	public CarVO(boolean free, String carID,
			Calendar useTime, ImageIcon imag, String engineID, String nameID,
			String chassisID, Calendar buyTime) {
		super(InfoEnum.CAR);
		this.free = free;
		this.carID = carID;
		this.useTime = useTime;
		this.imag = imag;
		this.engineID = engineID;
		this.nameID = nameID;
		this.chassisID = chassisID;
		this.buyTime = buyTime;
	}


	public CarVO(CarPO po){
		this(po.isFree(), po.getCarID(), po.getUseTime(), po.getImg(), po.getEngineID(), po.getNameID(), po.getChassisID(), po.getBuyTime());

	}
	//
	public CarPO toPO(){
		return new CarPO(free, carID, (Calendar)useTime.clone(), imag, engineID, nameID, chassisID, (Calendar)buyTime.clone());
	}
	//


	public Calendar getUseTime() {
		return useTime;
	}


	public String getNameID() {
		return nameID;
	}


	public void setUseTime(Calendar useTime) {
		this.useTime = useTime;
	}


	public void setNameID(String nameID) {
		this.nameID = nameID;
	}


	public String getCarID() {
		return carID;
	}


	public boolean isFree() {
		return free;
	}


	public ImageIcon getImag() {
		return imag;
	}


	public String getEngineID() {
		return engineID;
	}


	public String getChassisID() {
		return chassisID;
	}


	public Calendar getBuyTime() {
		return buyTime;
	}


	public void setFree(boolean free) {
		this.free = free;
	}


	public void setCarID(String carID) {
		this.carID = carID;
	}


	public void setImag(ImageIcon imag) {
		this.imag = imag;
	}


	public void setEngineID(String engineID) {
		this.engineID = engineID;
	}


	public void setChassisID(String chassisID) {
		this.chassisID = chassisID;
	}


	public void setBuyTime(Calendar buyTime) {
		this.buyTime = buyTime;
	}

}
