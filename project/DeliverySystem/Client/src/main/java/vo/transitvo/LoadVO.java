package vo.transitvo;

import java.util.ArrayList;
import java.util.Calendar;

import po.FormEnum;
import po.transportdata.LoadPO;

public class LoadVO extends TransitVO {

	String	peopletransport;
	String	expense;

	public LoadVO(String formID){
		super(FormEnum.TRANSPORT_HALL,formID);
	}
	
	public LoadVO(String formID,String truckID,String peopletransport,String expense,
			Calendar LoadDate,String TransportID,String placeTo,String	peopleSee,ArrayList<String> IDs){
		this(formID);

		this.peopletransport = peopletransport;
		this.expense = expense;
		this.LoadDate = LoadDate;
		this.TransportID = TransportID;
		this.placeTo = placeTo;
		this.peopleSee = peopleSee;
		this.IDs=IDs;
	}
	
	public LoadVO(LoadPO po){
		this(po.getFormID(),po.getTransportID(), po.getPeopleTransport(), po.getExpense(),po.getLoadDate(), po.getTransportID(), po.getPlaceTo(), po.getPeopleSee()
				,po.getIDs());
	}
	public LoadPO toPO(){
		ArrayList<String> idPO=this.selfDeepClone(IDs);
		
		return new LoadPO(formID, peopletransport, LoadDate, TransportID, placeTo, peopleSee, expense, idPO);
	}
}
