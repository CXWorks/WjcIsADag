package po.transportdata;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import po.FormEnum;
import po.FormPO;
import po.receivedata.StateEnum;

public class CenterOutPO extends TransportPO implements Serializable {

	private String placeFrom;// 出发地
	private String shelfNum;// 货柜号
	private TransportationEnum transitState;// 转运类型

	public CenterOutPO(String placeFrom, String shelfNum, String transitState,
			Calendar LoadDate, String TransportID, String placeTo,
			String peopleSee, String expense, ArrayList<String> IDs) {
		super();

		this.placeFrom = placeFrom;
		this.shelfNum = shelfNum;
		this.setTransitState(transitState);
		this.LoadDate = LoadDate;
		this.TransportID = TransportID;
		this.placeTo = placeTo;
		this.peopleSee = peopleSee;
		this.expense = expense;
		this.IDs = IDs;
		this.formType = FormEnum.TRANSPORT_CENTER;
	}

	public CenterOutPO(String placeFrom, String shelfNum, String transitState,
			Timestamp LoadDate, String TransportID, String placeTo,
			String peopleSee, String expense, ArrayList<String> IDs) {
		super();

		this.placeFrom = placeFrom;
		this.shelfNum = shelfNum;
		this.setTransitState(transitState);
		Calendar temp = Calendar.getInstance();
		temp.setTime(LoadDate);
		this.LoadDate = temp;
		this.TransportID = TransportID;
		this.placeTo = placeTo;
		this.peopleSee = peopleSee;
		this.expense = expense;
		this.IDs = IDs;
		this.formType = FormEnum.TRANSPORT_CENTER;
	}

	public String getPlaceFrom() {
		return placeFrom;
	}

	public String getShelfNum() {
		return shelfNum;
	}

	public TransportationEnum getTransitState() {
		return transitState;
	}

	public void setTransitState(String state) {
		switch (state) {
		case "CAR":
			this.transitState = TransportationEnum.CAR;
			break;
		case "PLANE":
			this.transitState = TransportationEnum.PLANE;
			break;
		case "TRAIN":
			this.transitState = TransportationEnum.TRAIN;
			break;
		}
	}

}
