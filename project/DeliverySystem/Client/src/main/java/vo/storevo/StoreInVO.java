package vo.storevo;

import model.store.StoreLocation;
import vo.FormVO;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreInVO extends FormVO {
	
	public StoreInVO(){
		
	}
	
    public StoreInVO(String orderID, String date, String destination,
			StoreLocation location) {
		super();
		this.orderID = orderID;
		this.date = date;
		this.destination = destination;
		this.location = location;
	}
    
	private String	orderID;
    private String	date;
    private String	destination;
    private StoreLocation location;
}
