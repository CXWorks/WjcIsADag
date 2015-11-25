package bl.blImpl.transportbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import message.CheckFormMessage;
import message.OperationMessage;
import po.FormEnum;
import po.orderdata.OrderPO;
import po.transportdata.CenterOutPO;
import rmi.examineService.ExamineSubmitService;
import rmi.orderdata.OrderDataService;
import rmi.transportdata.CenterOutDataService;
import tool.draft.DraftService;
import tool.vopo.VOPOFactory;
import userinfo.UserInfo;
import vo.FormVO;
import vo.ordervo.OrderVO;
import vo.transitvo.CenterOutVO;
import bl.blService.transportblService.TransportCenterBLService;
import bl.clientNetCache.CacheHelper;
import po.transportdata.CenterOutPO;

public class TransportCenterBLImpl implements TransportCenterBLService {
	private DraftService draftService;
	private VOPOFactory vopoFactory;
	private CenterOutDataService centerOutDataService;
	public TransportCenterBLImpl(VOPOFactory vopoFactory,DraftService draftService){
		this.draftService=draftService;
		this.vopoFactory=vopoFactory;
		this.centerOutDataService=CacheHelper.getTransportDataService();
	}

		
		public CenterOutVO loadDraft() {
			CenterOutVO vo=(CenterOutVO)draftService.getDraft(FormEnum.TRANSPORT_CENTER);
			return vo;
		}
		public OperationMessage saveDraft(CenterOutVO form) {
			return draftService.saveDraft(form);
		}
		public ArrayList<CheckFormMessage> checkFormat(CenterOutVO form,
				boolean isFinal) {
			// TODO Auto-generated method stub
			ArrayList<CheckFormMessage> result=new ArrayList<CheckFormMessage>();
			CheckFormMessage stub=new CheckFormMessage();
			result.add(stub);
			return result;

		}
		public OperationMessage submit(CenterOutVO form) {
			CenterOutPO po=(CenterOutPO)vopoFactory.transVOtoPO(form);
			ExamineSubmitService examineSubmitService=CacheHelper.getExamineSubmitService();
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
				ID = centerOutDataService.newID(UserInfo.getInstitutionID());
				return ID;
			} catch (RemoteException e) {
				return null;
			}
			
		}
		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportCenterBLService#getOrder(java.lang.String)
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
		


}
