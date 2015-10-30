package bl.blImpl.manangrbl;

import java.util.ArrayList;

import message.OperationMessage;
import vo.managevo.institution.CenterVO;
import vo.managevo.institution.HallVO;
import bl.blService.manageblService.ManageblCenterService;

/** 
 * Client//blImpl.manangrbl//ManageblCenterImpl.java
 * @author CXWorks
 * @date 2015年10月26日 上午8:35:57
 * @version 1.0 
 */
public class ManageblCenterImpl implements ManageblCenterService{

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCenterService#getCenter()
	 */
	public ArrayList<CenterVO> getCenter() {
		// TODO Auto-generated method stub
		ArrayList<CenterVO> result=new ArrayList<CenterVO>();
		CenterVO stub=new CenterVO();
		result.add(stub);
		return result;
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCenterService#addCenter(vo.managevo.institution.CenterVO)
	 */
	public OperationMessage addCenter(CenterVO center) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCenterService#deleteCenter(vo.managevo.institution.CenterVO)
	 */
	public OperationMessage deleteCenter(CenterVO center) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCenterService#modifyCenter(vo.managevo.institution.CenterVO)
	 */
	public OperationMessage modifyCenter(CenterVO center) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCenterService#searchCenter(vo.managevo.institution.CenterVO)
	 */
	public CenterVO searchCenter(CenterVO center) {
		// TODO Auto-generated method stub
		return new CenterVO();
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCenterService#newInstitutionDistance(java.lang.String, java.lang.Object)
	 */
	public OperationMessage newInstitutionDistance(String ID, Object ob) {
		// TODO Auto-generated method stub
		return null;
	}

}
