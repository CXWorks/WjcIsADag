package bl.blImpl.manangrbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.memberdata.DriverPO;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import rmi.memberdata.MemberDataService;
import message.OperationMessage;
import userinfo.UserInfo;
import vo.FormVO;
import vo.managevo.staff.DriverVO;
import vo.managevo.staff.StaffVO;
import bl.blService.manageblService.ManageblStaffService;
import bl.clientNetCache.CacheHelper;
import tool.vopo.VOPOFactory;

/**
 * Client//blImpl.manangrbl//ManageblStaffImpl.java
 * @author CXWorks
 * @date 2015年10月26日 上午8:32:19
 * @version 1.0
 */
public class StaffManage implements ManageblStaffService {
	private MemberDataService<StaffPO> memberDataService;

	private VOPOFactory vopoFactory;
	public StaffManage(VOPOFactory vopoFactory){
		memberDataService=CacheHelper.getMemberDataService_staff();
		this.vopoFactory=vopoFactory;
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblStaffService#modifyStaff(vo.managevo.staff.StaffVO)
	 */
	public OperationMessage modifyStaff(StaffVO after) {
		StaffPO po=(StaffPO)vopoFactory.transVOtoPO(after);
		try {
			return memberDataService.modifyStaff(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblStaffService#addStaff(vo.managevo.staff.StaffVO)
	 */
	public OperationMessage addStaff(StaffVO staff) {
		StaffPO po=(StaffPO)vopoFactory.transVOtoPO(staff);
		try {
			return memberDataService.addStaff(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblStaffService#dismissStaff(vo.managevo.staff.StaffVO)
	 */
	public OperationMessage dismissStaff(StaffVO staff) {
		StaffPO po=(StaffPO)vopoFactory.transVOtoPO(staff);
		try {
			return memberDataService.dismissStaff(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblStaffService#searchStaff(vo.managevo.staff.StaffVO)
	 */
	public StaffVO searchStaff(String staffID) {
		try {
			StaffPO po=memberDataService.getPerson(staffID);
			StaffVO vo=(StaffVO)vopoFactory.transPOtoVO(po);
			return vo;
		} catch (RemoteException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblStaffService#newStaffID()
	 */
	public String newStaffID(StaffTypeEnum staffType,String unitID) {
		try {
			String newStaffID=memberDataService.newStaffID(staffType, unitID);
			return newStaffID;
		} catch (RemoteException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.manageblService.ManageblStaffService#getStaff(po.memberdata.StaffTypeEnum)
	 */
	public ArrayList<StaffVO> getStaff(StaffTypeEnum staffType) {
		try {
			ArrayList<StaffPO> po=memberDataService.getStaff(staffType);
			ArrayList<StaffVO> result=new ArrayList<StaffVO>(po.size());
			for(int i=0;i<po.size();i++){
				StaffPO each=po.get(i);
				StaffVO vo=(StaffVO)vopoFactory.transPOtoVO(each);
				result.add(vo);
			}
			return result;
		} catch (RemoteException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.manageblService.ManageblStaffService#getStaffByInstitution()
	 */
	@Override
	public ArrayList<StaffVO> getStaffByInstitution() {
		try {
			if (memberDataService==null) {
			}
			ArrayList<StaffPO> po=memberDataService.getStaffByInstitution("0040001");
			ArrayList<StaffVO> result=new ArrayList<StaffVO>(po.size());
			for(int i=0;i<po.size();i++){
				StaffPO each=po.get(i);
				StaffVO vo=(StaffVO)vopoFactory.transPOtoVO(each);
				result.add(vo);
			}
			return result;
		} catch (RemoteException e) {
			return null;
		}
	}

}
