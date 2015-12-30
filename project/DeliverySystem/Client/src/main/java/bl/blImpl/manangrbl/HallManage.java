package bl.blImpl.manangrbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import message.OperationMessage;
import po.companydata.CenterPO;
import po.companydata.HallPO;
import rmi.companydata.CompanyDataCenterService;
import rmi.companydata.CompanyDataHallService;
import tool.vopo.VOPOFactory;
import vo.FormVO;
import vo.managevo.institution.HallVO;
import bl.NetReconnect.Reconnect;
import bl.blService.manageblService.ManageblHallService;
import bl.clientNetCache.CacheHelper;

/** 
 * Client//blImpl.manangrbl//ManageblHallImpl.java
 * @author CXWorks
 * @date 2015年10月26日 上午8:34:07
 * @version 1.0 
 */
public class HallManage implements ManageblHallService {
	private VOPOFactory vopoFactory;
	public HallManage(VOPOFactory vopoFactory){
		this.vopoFactory=vopoFactory;
		
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblHallService#getHall()
	 */
	public ArrayList<HallVO> getHall() {
		CompanyDataHallService hallService=CacheHelper.getCompanyDataHallService();
		try {
			ArrayList<HallPO> po=hallService.getHall();
			ArrayList<HallVO> result=new ArrayList<HallVO>(po.size());
			for(int i=0;i<po.size();i++){
				HallPO each=po.get(i);
				HallVO temp=(HallVO)vopoFactory.transPOtoVO(each);
				result.add(temp);
			}
			return result;
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblHallService#addHall(vo.managevo.institution.HallVO)
	 */
	public OperationMessage addHall(HallVO hall) {
		CompanyDataHallService hallService=CacheHelper.getCompanyDataHallService();
		hall.setInstitutionID(newHallID(nearCenterID(hall.getCity())));
		HallPO po=(HallPO)vopoFactory.transVOtoPO(hall);
		try {
			return hallService.addHall(po);
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblHallService#modifyHall(vo.managevo.institution.HallVO)
	 */
	public OperationMessage modifyHall(HallVO hall) {
		CompanyDataHallService hallService=CacheHelper.getCompanyDataHallService();
		HallPO po=(HallPO)vopoFactory.transVOtoPO(hall);
		try {
			return hallService.modifyHall(po);
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblHallService#deleteHall(po.companydata.HallPO)
	 */
	public OperationMessage deleteHall(HallVO hall) {
		CompanyDataHallService hallService=CacheHelper.getCompanyDataHallService();
		HallPO po=(HallPO)vopoFactory.transVOtoPO(hall);
		try {
			return hallService.deleteHall(po);
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblHallService#searchHall(vo.managevo.institution.HallVO)
	 */
	public HallVO searchHall(String hall) {
		CompanyDataHallService hallService=CacheHelper.getCompanyDataHallService();
		String ID=hall;
		ArrayList<HallPO> po;
		try {
			po = hallService.getHall();
			HallPO each=null;
			boolean found=false;
			for(int i=0;i<po.size();i++){
				each=po.get(i);
				if (each.getHallID().equalsIgnoreCase(ID)) {
					found=true;
					break;
				}
			}
			if (found) {
				HallVO vo=(HallVO)vopoFactory.transPOtoVO(each);
				return vo;
			} else {
				return null;
			}
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblHallService#newHallID()
	 */
	public String newHallID(String centerID) {
		CompanyDataHallService hallService=CacheHelper.getCompanyDataHallService();
		try {
			String ID=hallService.newHallID(centerID.substring(0, 3));
			return ID;
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.manageblService.ManageblHallService#nearCenterID(java.lang.String)
	 */
	@Override
	public String nearCenterID(String cityID) {
		CompanyDataCenterService companyDataCenterService=CacheHelper.getCompanyDataCenterService();
		try {
			List<CenterPO> centerPOs=companyDataCenterService.getCenter();
			String ans=centerPOs.stream()
					.map(center->center.getCityID())
					.filter(id->id.equalsIgnoreCase(cityID))
					.findFirst().get();
			return ans;
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return null;
		}
	}



}
