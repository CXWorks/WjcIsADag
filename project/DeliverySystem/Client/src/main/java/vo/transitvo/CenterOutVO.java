package vo.transitvo;

import java.util.ArrayList;
import java.util.Calendar;

import po.FormEnum;
import po.transportdata.CenterOutPO;

public class CenterOutVO extends TransitVO  {

	String	placeFrom;
	String	TransID;
	String	shelfNum;
	
	public CenterOutVO(){
		super(FormEnum.TRANSPORT_CENTER);
	}
	
	public CenterOutVO(String placeFrom,String TransID,String shelfNum,
			Calendar LoadDate,String LoadID,String placeTo,String	peopleSee){
		this();
		this.placeFrom = placeFrom;
		this.TransID = TransID;
		this.shelfNum = shelfNum;
		this.LoadDate = LoadDate;
		this.LoadID = LoadID;
		this.placeTo = placeTo;
		this.peopleSee = peopleSee;
	}
	public CenterOutVO(CenterOutPO po){
		this(po.getPlaceFrom(), po.getTransID(), po.getShelfNum(),po.getLoadDate(), po.getLoadID(), po.getPlaceTo(), po.getPeopleSee());
	}
	
	
}
