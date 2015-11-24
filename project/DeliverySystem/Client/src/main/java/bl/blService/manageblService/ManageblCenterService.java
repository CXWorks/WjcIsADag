package bl.blService.manageblService;

import java.util.ArrayList;

import message.OperationMessage;
import vo.managevo.institution.CenterVO;

/** 
 * Client//blService.manageblService//ManageblCenterService.java
 * @author CXWorks
 * @date 2015年10月25日 下午4:09:19
 * @version 1.0 
 */
public interface ManageblCenterService {
	
	public ArrayList<CenterVO> getCenter();
	
	public OperationMessage addCenter(CenterVO center);
	
	public OperationMessage deleteCenter(CenterVO center);
	
	public OperationMessage modifyCenter(CenterVO center);
	
	public CenterVO searchCenter(CenterVO center);
	
	public String newCenterID(String city);
	/**
	 * 这个涉及到新建的距离问题，2015/11/26前搁置
	 * @param ID
	 * @param ob
	 * @return
	 */
	public OperationMessage newInstitutionDistance(String ID,Object ob);
	
}
