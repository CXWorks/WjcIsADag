package vo.transitvo;

import java.util.ArrayList;
import java.util.Calendar;

import po.FormEnum;
import po.transportdata.CenterOutPO;
import po.transportdata.TransportationEnum;

public class CenterOutVO extends TransitVO  {

	private String	placeFrom;//出发地
	private String	shelfNum;//货柜号
	private TransportationEnum transitState;//转运类型
	public CenterOutVO(String formID){
		super(FormEnum.TRANSPORT_CENTER,formID);
	}
	
	public CenterOutVO(String formID,String placeFrom,String TransportID,String shelfNum,
			Calendar LoadDate,String expense,String placeTo,String	peopleSee,ArrayList<String> IDs,TransportationEnum transitState){
		this(formID);
		this.placeFrom = placeFrom;
		this.TransportID = TransportID;
		this.shelfNum = shelfNum;
		this.LoadDate = LoadDate;
		this.placeTo = placeTo;
		this.peopleSee = peopleSee;
		this.transitState =transitState;
		this.expense = expense;
		this.IDs=IDs;
	}
	public CenterOutVO(CenterOutPO po){
		this(po.getFormID(),po.getPlaceFrom(), po.getTransportID(), po.getShelfNum(),po.getLoadDate(), po.getExpense(), po.getPlaceTo(), po.getPeopleSee(),po.getIDs()
				,po.getTransitState());
	}
	
	
}
