package po.transportdata;

import java.io.Serializable;
import java.util.ArrayList;

import po.FormPO;

public class CenterOutPO extends TransportPO implements Serializable{
	
	private String	placeFrom;//出发地
	private String	shelfNum;//货柜号
	private TransportationEnum transitState;//转运类型
	public String getPlaceFrom() {
		return placeFrom;
	}
	public String getShelfNum() {
		return shelfNum;
	}
	public TransportationEnum getTransitState() {
		return transitState;
	}
	
}
