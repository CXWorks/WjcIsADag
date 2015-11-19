package vo.storevo;

import java.util.Calendar;

import po.FormEnum;
import po.storedata.StoreInPO;
import model.store.StoreLocation;
import vo.FormVO;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreInVO extends FormVO {
	
	public StoreInVO(){
		super(FormEnum.STORE_IN);
	}
	
    public StoreInVO(String orderID, Calendar date, String destination,
			StoreLocation location) {
		this();
		this.orderID = orderID;
		this.date = date;
		this.destination = destination;
		this.location = location;
	}
    
    public StoreInVO(StoreInPO po){
    	this(po.getOrderID(),po.getDate(), po.getDestination(), po.getLocation());
    }
    
	private String	orderID;
    private Calendar	date;
    private String	destination;
    private StoreLocation location;
}
