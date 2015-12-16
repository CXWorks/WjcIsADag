package vo.transitvo;

import java.util.ArrayList;
import java.util.Calendar;

import po.FormEnum;
import po.FormStateEnum;
import util.R.string;
import vo.FormVO;

public abstract class TransitVO extends FormVO{
	protected Calendar LoadDate;//装车日期
	protected String  TransportID;//汽运编号、航班列次号 等
	protected String	placeTo;//到达地
	protected String	peopleSee;//监装员
	protected String  expense;//运费
	protected ArrayList<String> IDs;//本次装箱所有托运单号
	//

	//
	protected ArrayList<String> selfDeepClone(ArrayList<String> po){
		ArrayList<String> ans=new ArrayList<String>(po.size());
		for(int i=0;i<po.size();i++){
			ans.add(po.get(i));
		}
		return ans;
	}
	protected TransitVO(FormEnum formEnum,String formID,String createrID){
		super(formEnum,FormStateEnum.CONSTRUCTED,formID,createrID);
	}
	public Calendar getLoadDate() {
		return LoadDate;
	}
	public String getTransportID() {
		return TransportID;
	}
	public String getPlaceTo() {
		return placeTo;
	}
	public String getPeopleSee() {
		return peopleSee;
	}
	public String getExpense() {
		return expense;
	}
	public ArrayList<String> getIDs() {
		return IDs;
	}


}
