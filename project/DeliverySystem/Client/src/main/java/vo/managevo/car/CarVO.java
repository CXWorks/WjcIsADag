package vo.managevo.car;

import java.util.Calendar;

import javax.swing.ImageIcon;

import po.InfoEnum;
import po.companydata.CarPO;
import vo.InfoVO;

public class CarVO extends InfoVO{
	private boolean free;
	private int carID;
	private Calendar useTime;
	private ImageIcon imag;
	//以下为无用内容，67脑洞真大
	private int engineID;
	private int nameID;
	private int chassisID;//chassis是车辆底盘的意思
	private Calendar buyTime;
	//
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
	public CarVO(){
		super(InfoEnum.CAR);
	}
	
	public CarVO(boolean free, int carID, Calendar useTime, ImageIcon imag,
			int engineID, int nameID, int chassisID, Calendar buyTime) {
		this();
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
}
