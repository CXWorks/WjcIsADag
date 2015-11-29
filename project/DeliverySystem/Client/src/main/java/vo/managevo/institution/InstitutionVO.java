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
	protected InstitutionVO(InfoEnum infoEnum) {
		// TODO Auto-generated constructor stub
		super(infoEnum);
	}
	protected ArrayList<StaffVO> selfDeepClonePOtoVO(ArrayList<StaffPO> po){
		ArrayList<StaffVO> ans=new ArrayList<StaffVO>(po.size());
		for(int i=0;i<po.size();i++){
			ans.add(new StaffVO(po.get(i)));
		}
		return ans;
	}
}
