package bl.blService.manageblService;

import java.util.ArrayList;

import message.OperationMessage;
import vo.managevo.staff.StaffVO;

/** 
 * Client//blService.manageblService//ManageblStaffService.java
 * @author CXWorks
 * @date 2015年10月25日 下午2:48:14
 * @version 1.0 
 */
public interface ManageblStaffService {
	
	public ArrayList<StaffVO> getStaff();
	
	public OperationMessage modifyStaff(StaffVO after);
	
	public OperationMessage addStaff(StaffVO staff);
	
	public OperationMessage dismissStaff(StaffVO staff);
	
	public StaffVO searchStaff(StaffVO staff);
	
	public String newStaffID();
}
