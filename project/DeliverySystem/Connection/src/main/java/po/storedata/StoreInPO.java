package po.storedata;

import java.sql.Timestamp;
import java.util.Calendar;

import model.store.StoreLocation;
import po.FormEnum;
import po.FormPO;
import po.receivedata.StateEnum;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreInPO extends FormPO {

	private String orderID;
	private Calendar date;
	private String destination;
	private StoreLocation location;
	private String money;

	public String getMoney() {
		return money;
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

	public StoreLocation getLocation() {
		return location;
	}

	/**
	 * @param formEnum
	 * @param formID
	 * @param orderID
	 * @param date
	 * @param destination
	 * @param location
	 */
	public StoreInPO(String formID, String orderID, Calendar date,
			String destination, StoreLocation location,String creatorID) {
		super(FormEnum.STORE_IN, formID,creatorID);
		this.orderID = orderID;
		this.date = date;
		this.destination = destination;
		this.location = location;
	}

	public StoreInPO(String formID, String orderID, Timestamp date,
			String destination, String location,String creatorID) {
		super(FormEnum.STORE_IN, formID,creatorID);
		this.orderID = orderID;
		Calendar temp = Calendar.getInstance();
		temp.setTime(date);
		this.date = temp;
		this.destination = destination;
		this.location = new StoreLocation(location);
	}

}
