package bl.blImpl.manangrbl;

import java.util.ArrayList;

import message.OperationMessage;
import vo.FormVO;
import vo.managevo.staff.StaffVO;
import bl.blService.manageblService.ManageblStaffService;

/** 
 * Client//blImpl.manangrbl//ManageblStaffImpl.java
 * @author CXWorks
 * @date 2015年10月26日 上午8:32:19
 * @version 1.0 
 */
public class ManageblStaffImpl implements ManageblStaffService {

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblStaffService#getStaff()
	 */
	public ArrayList<StaffVO> getStaff() {
		// TODO Auto-generated method stub
		ArrayList<StaffVO> result=new ArrayList<StaffVO>();
		StaffVO stub=new StaffVO();
		result.add(stub);
		return result;
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblStaffService#modifyStaff(vo.managevo.staff.StaffVO)
	 */
	public OperationMessage modifyStaff(StaffVO after) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblStaffService#addStaff(vo.managevo.staff.StaffVO)
	 */
	public OperationMessage addStaff(StaffVO staff) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblStaffService#dismissStaff(vo.managevo.staff.StaffVO)
	 */
	public OperationMessage dismissStaff(StaffVO staff) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblStaffService#searchStaff(vo.managevo.staff.StaffVO)
	 */
	public StaffVO searchStaff(StaffVO staff) {
		// TODO Auto-generated method stub
		return new StaffVO();
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblStaffService#newStaffID()
	 */
	public String newStaffID() {
		// TODO Auto-generated method stub
		return "11111111";
	}

}
