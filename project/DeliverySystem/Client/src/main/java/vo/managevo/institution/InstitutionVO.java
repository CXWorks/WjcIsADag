package vo.managevo.institution;

import java.util.ArrayList;

import po.InfoEnum;
import po.memberdata.StaffPO;
import vo.InfoVO;
import vo.managevo.staff.StaffVO;

/** 
 * Client//vo.managevo.institution//InstitutionVO.java
 * @author CXWorks
 * @date 2015年11月19日 下午2:28:30
 * @version 1.0 
 */
public abstract class InstitutionVO extends InfoVO {
	/**
	 * 
	 */ 
	protected String institutionID;
	protected String city;
	protected InstitutionVO(InfoEnum infoEnum) {
		super(infoEnum);
	}
	protected ArrayList<StaffVO> selfDeepClonePOtoVO(ArrayList<StaffPO> po){
		ArrayList<StaffVO> ans=new ArrayList<StaffVO>(po.size());
		for(int i=0;i<po.size();i++){
			ans.add(new StaffVO(po.get(i)));
		}
		return ans;
	}
	public String getInstitutionID() {
		return institutionID;
	}
	public String getCity() {
		return city;
	}
	public void setInstitutionID(String institutionID) {
		this.institutionID = institutionID;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
