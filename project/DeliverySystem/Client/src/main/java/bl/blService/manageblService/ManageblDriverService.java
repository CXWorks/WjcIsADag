package bl.blService.manageblService;

import java.util.ArrayList;

import message.OperationMessage;
import po.memberdata.StaffTypeEnum;
import vo.managevo.staff.DriverVO;
import vo.managevo.staff.StaffVO;

/** 
 * Client//bl.blService.manageblService//ManageblDriverService.java
 * @author CXWorks
 * @date 2015年12月6日 上午8:04:37
 * @version 1.0 
 */
public interface ManageblDriverService {
	public DriverVO searchDriver(String  driverID);
	
	public ArrayList<DriverVO> getStaffByInstitution();
	
	
	public OperationMessage modifyStaff(DriverVO after);
	
	public OperationMessage addStaff(DriverVO staff);
	
	public OperationMessage dismissStaff(DriverVO staff);
	
	/**
	 * 员工类型和所属机构编号（没有就传null）
	 * @return
	 */
	public String newStaffID(StaffTypeEnum staffType,String unitID);
}
