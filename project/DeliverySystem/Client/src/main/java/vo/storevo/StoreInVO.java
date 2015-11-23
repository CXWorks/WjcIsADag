package vo.storevo;

import java.util.Calendar;

import po.FormEnum;
import po.FormStateEnum;
import po.storedata.StoreInPO;
import model.store.StoreLocation;
import vo.FormVO;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreInVO extends FormVO {
	
	public StoreInVO(String formID){
		super(FormEnum.STORE_IN,FormStateEnum.CONSTRUCTED,formID);
	}
	
    public StoreInVO(String formID,String orderID, Calendar date, String destination,
			StoreLocation location) {
		this(formID);
		this.orderID = orderID;
		this.date = date;
		this.destination = destination;
		this.location = location;
	}
    
    public StoreInVO(StoreInPO po){
    	this(po.getFormID(),po.getOrderID(),po.getDate(), po.getDestination(), po.getLocation());
    }
    
	private String	orderID;
    private Calendar	date;
    private String	destination;
    private StoreLocation location;
}
