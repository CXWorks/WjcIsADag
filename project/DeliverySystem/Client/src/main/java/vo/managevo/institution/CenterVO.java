package vo.managevo.institution;
import java.util.ArrayList;

import po.InfoEnum;
import po.companydata.CenterPO;
import po.memberdata.StaffPO;
import vo.InfoVO;
import vo.managevo.staff.StaffVO;


public class CenterVO extends InfoVO{
	
	public CenterVO(){
		super(InfoEnum.CENTER);
	}
	
	public CenterVO(String centerID, String city, ArrayList<StaffVO> storeman,
			ArrayList<StaffVO> counterman) {
		this();
		this.centerID = centerID;
		this.city = city;
		this.storeman = storeman;
		this.counterman = counterman;
	}

	private String centerID;
	private String city;
	private ArrayList<StaffVO> storeman;
	private ArrayList<StaffVO> counterman;
	//
	
	//
	private ArrayList<StaffVO> selfDeepClonePOtoVO(ArrayList<StaffPO> po){
		ArrayList<StaffVO> ans=new ArrayList<StaffVO>(po.size());
		for(int i=0;i<po.size();i++){
			ans.add(new StaffVO(po.get(i)));
		}
		return ans;
	}
	
}
