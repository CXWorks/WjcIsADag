package vo.transitvo;

import java.util.ArrayList;
import java.util.Calendar;

import po.FormEnum;
import po.transportdata.LoadPO;

public class LoadVO extends TransitVO {

	String	peopletransport;
	String	expense;

	public LoadVO(){
		super(FormEnum.TRANSPORT_HALL);
	}
	
	public LoadVO(String truckID,String peopletransport,String expense,
			Calendar LoadDate,String TransportID,String placeTo,String	peopleSee,ArrayList<String> IDs){
		this();

		this.peopletransport = peopletransport;
		this.expense = expense;
		this.LoadDate = LoadDate;
		this.TransportID = TransportID;
		this.placeTo = placeTo;
		this.peopleSee = peopleSee;
		this.IDs=IDs;
	}
	
	public LoadVO(LoadPO po){
		this(po.getTransportID(), po.getPeopleTransport(), po.getExpense(),po.getLoadDate(), po.getTransportID(), po.getPlaceTo(), po.getPeopleSee()
				,po.getIDs());
	}
}
