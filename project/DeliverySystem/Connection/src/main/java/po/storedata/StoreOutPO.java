package po.storedata;

import java.util.Calendar;

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
    //
	public String getOrderID() {
		return orderID;
	}
	public Calendar getDate() {
		return date;
	}
	public String getDestination() {
		return destination;
	}
	public TransportationEnum getTransportation() {
		return transportation;
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
			TransportationEnum transportation, String transID) {
		super(FormEnum.STORE_OUT, formID);
		this.orderID = orderID;
		this.date = date;
		this.destination = destination;
		this.transportation = transportation;
		this.transID = transID;
	}
    

}
