package blImpl.manangrbl;

import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.HallPO;
import vo.FormVO;
import vo.managevo.institution.HallVO;
import blService.manageblService.ManageblHallService;

/** 
 * Client//blImpl.manangrbl//ManageblHallImpl.java
 * @author CXWorks
 * @date 2015年10月26日 上午8:34:07
 * @version 1.0 
 */
public class ManageblHallImpl implements ManageblHallService {

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblHallService#getHall()
	 */
	public ArrayList<HallVO> getHall() {
		// TODO Auto-generated method stub
		ArrayList<HallVO> result=new ArrayList<HallVO>();
		HallVO stub=new HallVO();
		result.add(stub);
		return result;
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblHallService#addHall(vo.managevo.institution.HallVO)
	 */
	public OperationMessage addHall(HallVO hall) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblHallService#modifyHall(vo.managevo.institution.HallVO)
	 */
	public OperationMessage modifyHall(HallVO hall) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblHallService#deleteHall(po.companydata.HallPO)
	 */
	public OperationMessage deleteHall(HallPO hall) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblHallService#searchHall(vo.managevo.institution.HallVO)
	 */
	public HallVO searchHall(HallVO hall) {
		// TODO Auto-generated method stub
		return new HallVO();
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblHallService#newHallID()
	 */
	public String newHallID() {
		// TODO Auto-generated method stub
		return "11111";
	}

}
