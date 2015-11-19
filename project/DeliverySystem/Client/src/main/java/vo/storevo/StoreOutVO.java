package vo.storevo;

import java.util.Calendar;

import po.FormEnum;
import po.storedata.StoreOutPO;
import po.transportdata.TransportationEnum;
import vo.FormVO;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreOutVO extends FormVO {
	public StoreOutVO(){
		super(FormEnum.STORE_OUT);
	}
	
    public StoreOutVO(String orderID, Calendar date, String destination,
			TransportationEnum transportation, String transID) {
		this();
		this.orderID = orderID;
		this.date = date;
		this.destination = destination;
		this.transportation = transportation;
		this.transID = transID;
	}
    //
    public StoreOutVO(StoreOutPO po){
    	this(po.getOrderID(), po.getDate(), po.getDestination(), po.getTransportation(), po.getTransID());
    }
    
	private String	orderID;
    private Calendar	date;
    private String	destination;
    TransportationEnum transportation;
    private String	transID;
}
