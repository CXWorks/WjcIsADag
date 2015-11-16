package vo.transitvo;

import java.util.ArrayList;

public class CenterOutVO extends TransitVO  {

	String	placeFrom;
	String	TransID;
	String	shelfNum;
	
	public CenterOutVO(){
		
	}
	
	public CenterOutVO(String placeFrom,String TransID,String shelfNum,
			String LoadDate,String LoadID,String placeTo,String	peopleSee){
		this.placeFrom = placeFrom;
		this.TransID = TransID;
		this.shelfNum = shelfNum;
		this.LoadDate = LoadDate;
		this.LoadID = LoadID;
		this.placeTo = placeTo;
		this.peopleSee = peopleSee;
	}

}
