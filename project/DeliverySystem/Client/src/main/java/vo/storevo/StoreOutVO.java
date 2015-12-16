package vo.storevo;

import java.util.Calendar;

import model.store.StoreLocation;
import po.FormEnum;
import po.FormStateEnum;
import po.storedata.StoreOutPO;
import po.transportdata.TransportationEnum;
import userinfo.UserInfo;
import vo.FormVO;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreOutVO extends StoreFormVO {
	private StoreOutVO(String formID,String createrID){
		super(FormEnum.STORE_OUT,FormStateEnum.CONSTRUCTED,formID,createrID);
	}

    public StoreOutVO(String formID,String orderID, Calendar date, String destination,
			TransportationEnum transportation, String transID,String createrID) {
		this(formID,createrID);
		this.orderID = orderID;
		this.date = date;
		this.destination = destination;
		this.transportation = transportation;
		this.transID = transID;
	}
    //
    public StoreOutVO(StoreOutPO po){
    	this(po.getFormID(),po.getOrderID(), po.getDate(), po.getDestination(), po.getTransportation(), po.getTransID(),po.getCreaterID());
    	this.setMoney(po.getMoney());
    	this.setLocation(po.getLocation());
    }

    private TransportationEnum transportation;
    private String	transID;

	//
    public StoreOutPO toPO(){
    	StoreOutPO storeOutPO= new StoreOutPO(formID, orderID, date, destination, transportation, transID,createrID);
    	storeOutPO.setCache_OperatorID(UserInfo.getUserID());
    	return storeOutPO;
    }

	/* (non-Javadoc)
	 * @see vo.FormVO#getMainInfo()
	 */
	@Override
	public String getMainInfo() {
		return destination+" 位置 "+" "+location.getArea().getChinese()+" "+location.getRow()+" "
				+" "+location.getShelf()+" "+location.getPosition();
	}
}
