package bl.blImpl.manangrbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.companydata.CenterPO;
import rmi.companydata.CompanyDataCenterService;
import tool.vopo.VOPOFactory;
import message.OperationMessage;
import vo.managevo.institution.CenterVO;
import vo.managevo.institution.HallVO;
import bl.blService.manageblService.ManageblCenterService;
import bl.clientNetCache.CacheHelper;

/** 
 * Client//blImpl.manangrbl//ManageblCenterImpl.java
 * @author CXWorks
 * @date 2015年10月26日 上午8:35:57
 * @version 1.0 
 */
public class CenterManage implements ManageblCenterService{
	private VOPOFactory vopoFactory;
	public CenterManage(VOPOFactory vopoFactory){
		this.vopoFactory=vopoFactory;
		
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCenterService#getCenter()
	 */
	public ArrayList<CenterVO> getCenter() {
		CompanyDataCenterService centerService=CacheHelper.getCompanyDataCenterService();
		try {
			ArrayList<CenterPO> po=centerService.getCenter();
			ArrayList<CenterVO> result=new ArrayList<CenterVO>(po.size());
			for (int i = 0; i < po.size(); i++) {
				CenterPO each=po.get(i);
				CenterVO temp=(CenterVO)vopoFactory.transPOtoVO(each);
				result.add(temp);
			}
			return result;
		} catch (RemoteException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCenterService#addCenter(vo.managevo.institution.CenterVO)
	 */
	public OperationMessage addCenter(CenterVO center) {
		CompanyDataCenterService centerService=CacheHelper.getCompanyDataCenterService();
		CenterPO po=(CenterPO)vopoFactory.transVOtoPO(center);
		try {
			return centerService.addCenter(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCenterService#deleteCenter(vo.managevo.institution.CenterVO)
	 */
	public OperationMessage deleteCenter(CenterVO center) {
		CompanyDataCenterService centerService=CacheHelper.getCompanyDataCenterService();
		CenterPO po=(CenterPO)vopoFactory.transVOtoPO(center);
		try {
			return centerService.deleteCenter(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCenterService#modifyCenter(vo.managevo.institution.CenterVO)
	 */
	public OperationMessage modifyCenter(CenterVO center) {
		CompanyDataCenterService centerService=CacheHelper.getCompanyDataCenterService();
		CenterPO po=(CenterPO)vopoFactory.transVOtoPO(center);
		try {
			return centerService.modifyCenter(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCenterService#searchCenter(vo.managevo.institution.CenterVO)
	 */
	public CenterVO searchCenter(String center) {
		CompanyDataCenterService centerService=CacheHelper.getCompanyDataCenterService();
		String ID=center;
		try {
			ArrayList<CenterPO> po=centerService.getCenter();
			CenterPO each=null;
			boolean found=false;
			for (int i = 0; i < po.size(); i++) {
				each=po.get(i);
				if (each.getCenterID().equalsIgnoreCase(ID)) {
					found=true;
					break;
				}
			}
			//
			if (found) {
				CenterVO vo=(CenterVO)vopoFactory.transPOtoVO(each);
				return vo;
			} else {
				return null;
			}
		} catch (RemoteException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCenterService#newInstitutionDistance(java.lang.String, java.lang.Object)
	 */
	public OperationMessage newInstitutionDistance(String ID, Object ob) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see bl.blService.manageblService.ManageblCenterService#newCenterID(java.lang.String)
	 */
	@Override
	public String newCenterID(String city) {
		CompanyDataCenterService centerService=CacheHelper.getCompanyDataCenterService();
		try {
			String ID=centerService.newCenterID(city);
			return ID;
		} catch (RemoteException e) {
			return null;
		}
		
	}

}
