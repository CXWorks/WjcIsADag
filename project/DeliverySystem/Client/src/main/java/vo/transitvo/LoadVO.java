package vo.transitvo;

import java.util.ArrayList;

public class LoadVO extends TransitVO {

	String	truckID;
	String	peopletransport;
	String	expense;

	public LoadVO(){
		
	}
	
	public LoadVO(String truckID,String peopletransport,String expense,
			String LoadDate,String LoadID,String placeTo,String	peopleSee){
		this.truckID = truckID;
		this.peopletransport = peopletransport;
		this.expense = expense;
		this.LoadDate = LoadDate;
		this.LoadID = LoadID;
		this.placeTo = placeTo;
		this.peopleSee = peopleSee;
	}
}
