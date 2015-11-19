package vo.transitvo;

import java.util.ArrayList;
import java.util.Calendar;

import po.FormEnum;
import po.transportdata.LoadPO;

public class LoadVO extends TransitVO {

	String	truckID;
	String	peopletransport;
	String	expense;

	public LoadVO(){
		super(FormEnum.TRANSPORT_HALL);
	}
	
	public LoadVO(String truckID,String peopletransport,String expense,
			Calendar LoadDate,String LoadID,String placeTo,String	peopleSee){
		this();
		this.truckID = truckID;
		this.peopletransport = peopletransport;
		this.expense = expense;
		this.LoadDate = LoadDate;
		this.LoadID = LoadID;
		this.placeTo = placeTo;
		this.peopleSee = peopleSee;
	}
	
	public LoadVO(LoadPO po){
		this(po.getTruckID(), po.getPeopletransport(), po.getExpense(),po.getLoadDate(), po.getLoadID(), po.getPlaceTo(), po.getPeopleSee());
	}
}
