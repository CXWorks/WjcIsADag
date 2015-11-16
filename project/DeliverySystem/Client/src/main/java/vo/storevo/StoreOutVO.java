package vo.storevo;

import po.transportdata.TransportationEnum;
import vo.FormVO;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreOutVO extends FormVO {
	public StoreOutVO(){}
	
    public StoreOutVO(String orderID, String date, String destination,
			TransportationEnum transportation, String transID) {
		super();
		this.orderID = orderID;
		this.date = date;
		this.destination = destination;
		this.transportation = transportation;
		this.transID = transID;
	}
    
	private String	orderID;
    private String	date;
    private String	destination;
    TransportationEnum transportation;
    private String	transID;
}
