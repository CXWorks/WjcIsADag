package po.storedata;

import java.sql.Timestamp;
import java.util.Calendar;

import model.store.StoreAreaCode;
import model.store.StoreLocation;
import po.FormEnum;
import po.FormPO;
import po.transportdata.TransportationEnum;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreOutPO extends FormPO {
    private String	orderID;
    private Calendar	date;
    private String	destination;
    TransportationEnum transportation;
    private String	transID;
    private String money;
    private StoreLocation location;
    //

	public String getMoney() {
		return money;
	}

	public StoreLocation getLocation() {
		return location;
	}

	public void setLocation(StoreLocation location) {
		this.location = location;
	}

	public void setLocation(String location) {
		this.location = new StoreLocation(location);
	}

	public void setMoney(String money) {
		this.money = money;
	}
	public String getOrderID() {
		return orderID;
	}
	public Calendar getDate() {
		return date;
	}
	public Timestamp getDateForSQL() {
		return new Timestamp(this.date.getTimeInMillis());
	}
	public String getDestination() {
		return destination;
	}
	public TransportationEnum getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		switch (transportation) {
		case "CAR":
			this.transportation = TransportationEnum.CAR;
			break;
		case "PLANE":
			this.transportation = TransportationEnum.PLANE;
			break;
		case "TRAIN":
			this.transportation = TransportationEnum.TRAIN;
			break;
		}
	}
	public String getTransID() {
		return transID;
	}
	/**
	 * @param formEnum
	 * @param formID
	 * @param orderID
	 * @param date
	 * @param destination
	 * @param transportation
	 * @param transID
	 */
	public StoreOutPO(String formID, String orderID,
			Calendar date, String destination,
			TransportationEnum transportation, String transID,String createrID) {
		super(FormEnum.STORE_OUT, formID ,createrID);
		this.orderID = orderID;
		this.date = date;
		this.destination = destination;
		this.transportation = transportation;
		this.transID = transID;
	}

	public StoreOutPO(String formID, String orderID,
			Timestamp date, String destination,
			String transportation, String transID) {
		super(FormEnum.STORE_OUT, formID);
		this.orderID = orderID;
		Calendar temp = Calendar.getInstance();
		temp.setTime(date);
		this.date = temp;
		this.destination = destination;
		this.setTransportation(transportation);
		this.transID = transID;
	}


}
