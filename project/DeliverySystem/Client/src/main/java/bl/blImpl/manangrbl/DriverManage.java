package bl.blImpl.manangrbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.memberdata.DriverPO;
import po.memberdata.StaffTypeEnum;
import rmi.memberdata.MemberDataService;
import tool.vopo.VOPOFactory;
import userinfo.UserInfo;
import vo.managevo.staff.DriverVO;
import bl.blService.manageblService.ManageblDriverService;
import bl.clientNetCache.CacheHelper;

/**
 * Client//bl.blImpl.manangrbl//DriverManage.java
 * @author CXWorks
 * @date 2015年12月6日 上午8:07:26
 * @version 1.0
 */
public class DriverManage implements ManageblDriverService {
	private VOPOFactory vopoFactory;

	public DriverManage(VOPOFactory vopoFactory){
		
		this.vopoFactory=vopoFactory;
	}

	/* (non-Javadoc)
	 * @see bl.blService.manageblService.ManageblDriverService#searchDriver(java.lang.String)
	 */
	@Override
	public DriverVO searchDriver(String driverID) {
		MemberDataService<DriverPO> memberDataService_driver=CacheHelper.getMemberDataService_driver();
		try {
			DriverPO po=memberDataService_driver.getPerson(driverID);
			DriverVO vo=(DriverVO)vopoFactory.transPOtoVO(po);
			return vo;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.manageblService.ManageblDriverService#getStaffByInstitution()
	 */
	@Override
	public ArrayList<DriverVO> getStaffByInstitution() {
		MemberDataService<DriverPO> memberDataService_driver=CacheHelper.getMemberDataService_driver();
		try {
			ArrayList<DriverPO> driverPOs = memberDataService_driver.getStaffByInstitution(UserInfo.getInstitutionID());
			ArrayList<DriverVO> vo = new ArrayList<DriverVO>(driverPOs.size());
			for (DriverPO driverPO : driverPOs) {
				System.out.println(")))))"+driverPO.getInfoEnum());
				DriverVO temp=(DriverVO)vopoFactory.transPOtoVO(driverPO);
				vo.add(temp);
			}
			return vo;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}

	}

	/* (non-Javadoc)
	 * @see bl.blService.manageblService.ManageblDriverService#modifyStaff(vo.managevo.staff.DriverVO)
	 */
	@Override
	public OperationMessage modifyStaff(DriverVO after) {
		MemberDataService<DriverPO> memberDataService_driver=CacheHelper.getMemberDataService_driver();
		DriverPO po=(DriverPO)vopoFactory.transVOtoPO(after);
		try {
			return memberDataService_driver.modifyStaff(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new OperationMessage(false, e.getMessage());
		}

	}

	/* (non-Javadoc)
	 * @see bl.blService.manageblService.ManageblDriverService#addStaff(vo.managevo.staff.DriverVO)
	 */
	@Override
	public OperationMessage addStaff(DriverVO staff) {
		MemberDataService<DriverPO> memberDataService_driver=CacheHelper.getMemberDataService_driver();
		DriverPO po=(DriverPO)vopoFactory.transVOtoPO(staff);
		try {
			return memberDataService_driver.addStaff(po);
		} catch (RemoteException e) {
			e.printStackTrace();
			return new OperationMessage(false, e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.manageblService.ManageblDriverService#dismissStaff(vo.managevo.staff.DriverVO)
	 */
	@Override
	public OperationMessage dismissStaff(DriverVO staff) {
		MemberDataService<DriverPO> memberDataService_driver=CacheHelper.getMemberDataService_driver();
		DriverPO po=(DriverPO)vopoFactory.transVOtoPO(staff);
		try {
			return memberDataService_driver.dismissStaff(po);
		} catch (RemoteException e) {
			e.printStackTrace();
			return new OperationMessage(false, e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.manageblService.ManageblDriverService#newStaffID(po.memberdata.StaffTypeEnum, java.lang.String)
	 */
	@Override
	public String newStaffID(StaffTypeEnum staffType, String unitID) {
		MemberDataService<DriverPO> memberDataService_driver=CacheHelper.getMemberDataService_driver();
		try {
			String ID=memberDataService_driver.newStaffID(staffType, unitID);
			return ID;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

}
