package vo.transitvo;

import java.util.ArrayList;

import vo.FormVO;

public abstract class TransitVO extends FormVO{
	String	LoadDate;
	String	LoadID;
	String	placeTo;
	String	peopleSee;
	ArrayList<String> IDs;
}
