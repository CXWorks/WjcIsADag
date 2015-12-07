package bl.blImpl.transportbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import message.CheckFormMessage;
import message.OperationMessage;
import po.FormEnum;
import po.companydata.CarPO;
import po.companydata.HallPO;
import po.memberdata.DriverPO;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import po.orderdata.OrderPO;
import po.transportdata.LoadPO;
import rmi.companydata.CompanyDataCarService;
import rmi.companydata.CompanyDataHallService;
import rmi.examineService.ExamineSubmitService;
import rmi.memberdata.MemberDataService;
import rmi.orderdata.OrderDataService;
import rmi.transportdata.LoadDataService;
import tool.draft.DraftService;
import tool.vopo.VOPOFactory;
import userinfo.UserInfo;
import vo.managevo.car.CarVO;
import vo.managevo.staff.DriverVO;
import vo.managevo.staff.StaffVO;
import vo.ordervo.OrderVO;
import vo.transitvo.LoadVO;
import bl.blService.transportblService.TransportHallBLService;
import bl.clientNetCache.CacheHelper;

public class TransportHallBLImpl implements TransportHallBLService {
	private LoadDataService loadDataService;
	private DraftService draftService;
	private VOPOFactory vopoFactory;
	public TransportHallBLImpl(VOPOFactory vopoFactory,DraftService draftService){
		this.loadDataService=CacheHelper.getLoadDataService();
		this.draftService=draftService;
		this.vopoFactory=vopoFactory;
	}



		public LoadVO loadDraft() {
			LoadVO vo=(LoadVO)draftService.getDraft(FormEnum.TRANSPORT_HALL);
			return vo;
		}
		public OperationMessage saveDraft(LoadVO form) {
			return draftService.saveDraft(form);
		}
		public ArrayList<CheckFormMessage> checkFormat(LoadVO form,
				boolean isFinal) {
			// TODO Auto-generated method stub
			ArrayList<CheckFormMessage> result=new ArrayList<CheckFormMessage>();
			CheckFormMessage stub=new CheckFormMessage();
			result.add(stub);
			return result;

		}
		public OperationMessage submit(LoadVO form) {
			ExamineSubmitService examineSubmitService=CacheHelper.getExamineSubmitService();
			LoadPO po=(LoadPO)vopoFactory.transVOtoPO(form);
			try {
				return examineSubmitService.submit(po);
			} catch (RemoteException e) {
				return new OperationMessage(false, "net error");
			}
		}
		/* (non-Javadoc)
		 * @see bl.blService.FormBLService#newID()
		 */
		public String newID() {
			String ID;
			try {
				ID = loadDataService.newID(UserInfo.getInstitutionID());
				return ID;
			} catch (RemoteException e) {
				return null;
			}

		}

		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportHallBLService#getCars(java.lang.String)
		 */
		public ArrayList<CarVO> getCars(String hallID) {
			CompanyDataCarService companyDataCarService=CacheHelper.getCompanyDataCarService();
			try {
				ArrayList<CarPO> po=companyDataCarService.getCars(hallID);
				ArrayList<CarVO> result=new ArrayList<CarVO>(po.size());
				for (int i = 0; i < po.size(); i++) {
					CarPO each=po.get(i);
					CarVO temp=(CarVO)vopoFactory.transPOtoVO(each);
					result.add(temp);
				}
				return result;
			} catch (RemoteException e) {
				return null;
			}

		}
		
		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportHallBLService#getOrder(java.lang.String)
		 */
		public OrderVO getOrder(String orderID) {
			OrderDataService orderDataService=CacheHelper.getOrderDataService();
			try {
				OrderPO po=orderDataService.getFormPO(orderID);
				OrderVO vo=(OrderVO)vopoFactory.transPOtoVO(po);
				return vo;
			} catch (RemoteException e) {
				return null;
			}
		}
		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportHallBLService#getDrivers(java.lang.String)
		 */
		@Override
		public ArrayList<DriverVO> getDrivers(String hallID) {
			MemberDataService<DriverPO> memberDataService=CacheHelper.getMemberDataService_driver();
			try {
				ArrayList<DriverPO> po=memberDataService.getStaff(StaffTypeEnum.DRIVER);
				ArrayList<DriverVO> vo=new ArrayList<DriverVO>(po.size());
				for (int i = 0; i < po.size(); i++) {
					DriverPO each=po.get(i);
					DriverVO temp=(DriverVO)vopoFactory.transPOtoVO(each);
					vo.add(temp);
				}
				return vo;
			} catch (RemoteException e) {
				return null;
			}
		}



		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportHallBLService#getLocation(java.lang.String)
		 */
		@Override
		public ArrayList<String> getLocation(String hallID) {
			CompanyDataHallService companyDataHallService=CacheHelper.getCompanyDataHallService();
			ArrayList<String> ans=new ArrayList<String>();
			try {
				HallPO hall=companyDataHallService.getHallByID(hallID);
				String centerID=hall.getNearCenterID();
				ans.add(centerID);
				ArrayList<HallPO> hallPOs=companyDataHallService.getHall();
				for (HallPO hallPO : hallPOs) {
					if (hallPO.getNearCenterID()==centerID&&hallPO.getHallID()!=hallID) {
						ans.add(hallPO.getHallID());
					}
				}
				return ans;
			} catch (RemoteException e) {
				e.printStackTrace();
				return null;
			}
			
		}
}
