package po.storedata;

import java.util.Calendar;

import model.store.StoreLocation;
import po.FormEnum;
import po.FormPO;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreInPO extends FormPO {

    private String	orderID;
    private Calendar	date;
    private String	destination;
    private StoreLocation location;
	public String getOrderID() {
		return orderID;
	}
	public Calendar getDate() {
		return date;
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
	public StoreInPO(String formID, String orderID,
			Calendar date, String destination, StoreLocation location) {
		super(FormEnum.STORE_IN, formID);
		this.orderID = orderID;
		this.date = date;
		this.destination = destination;
		this.location = location;
	}
    
    
}
