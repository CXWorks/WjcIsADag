package vo.storevo;

import java.util.Calendar;

import model.store.StoreLocation;
import po.FormEnum;
import po.FormStateEnum;
import vo.FormVO;

public abstract class StoreFormVO extends FormVO {

	protected String orderID;
	protected Calendar date;
	protected String destination;
	protected StoreLocation location;
	protected String money;

	protected StoreFormVO(FormEnum type, FormStateEnum state, String formID) {
		super(type, state, formID);
	}

	public String getIO(){
		switch (this.formType) {
		case STORE_IN:return "进库";
		case STORE_OUT:return "出库";
		default:
			return "非出入库单";
		}
	}

	public String getLocationForLog() {
		return location.getRow() + "排" + location.getShelf() + "架" + location.getPosition() + "号";
	}

	public void setLocation(StoreLocation location) {
		this.location = location;
	}

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

	public String getDestination() {
		return destination;
	}

}
