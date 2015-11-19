package vo.transitvo;

import java.util.ArrayList;
import java.util.Calendar;

import po.FormEnum;
import util.R.string;
import vo.FormVO;

public abstract class TransitVO extends FormVO{
	Calendar LoadDate;
	String	LoadID;
	String	placeTo;
	String	peopleSee;
	ArrayList<String> IDs;
	protected ArrayList<String> selfDeepClone(ArrayList<String> po){
		ArrayList<String> ans=new ArrayList<String>(po.size());
		for(int i=0;i<po.size();i++){
			ans.add(po.get(i));
		}
		return ans;
	}
	protected TransitVO(FormEnum formEnum){
		super(formEnum);
	}
}
