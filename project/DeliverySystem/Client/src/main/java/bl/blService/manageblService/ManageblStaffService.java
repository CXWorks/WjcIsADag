package bl.blService.manageblService;

import java.util.ArrayList;

import po.memberdata.StaffTypeEnum;
import message.OperationMessage;
import vo.managevo.staff.DriverVO;
import vo.managevo.staff.StaffVO;

/** 
 * Client//blService.manageblService//ManageblStaffService.java
 * @author CXWorks
 * @date 2015年10月25日 下午2:48:14
 * @version 1.0 
 */
public interface ManageblStaffService {
	public ArrayList<StaffVO> getStaffByInstitution();
	
	public ArrayList<StaffVO> getStaff(StaffTypeEnum staffType);
	
	public OperationMessage modifyStaff(StaffVO after);
	
	public OperationMessage addStaff(StaffVO staff);
	
	public OperationMessage dismissStaff(StaffVO staff);
	
	public StaffVO searchStaff(String staffID);
	/**
	 * 员工类型和所属机构编号（没有就传null）
	 * @return
	 */
	public String newStaffID(StaffTypeEnum staffType,String unitID);
	
	
}
