package bl.blImpl.deliverbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.FormEnum;
import po.deliverdata.DeliverPO;
import po.orderdata.OrderPO;
import rmi.deliverdata.DeliverDataService;
import rmi.orderdata.OrderDataService;
import message.CheckFormMessage;
import message.OperationMessage;
import userinfo.UserInfo;
import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;
import bl.blService.FormatCheckService.FormatCheckService;
import bl.blService.deliverblService.DeliverBLService;
import bl.clientNetCache.CacheHelper;
import tool.draft.DraftService;
import tool.vopo.VOPOFactory;

/** 
 * Client//bl.blImpl.deliverbl//DeliverBLController.java
 * @author CXWorks
 * @date 2015年11月15日 下午4:45:57
 * @version 1.0 
 */
public class DeliverBLImpl implements DeliverBLService {
	private DraftService draftService;
	private VOPOFactory vopoFactory;
	private FormatCheckService formatCheckService;
	private DeliverDataService deliverDataService;
	//
	public DeliverBLImpl(VOPOFactory vopoFactory,DraftService draftService,FormatCheckService formatCheckService){
		this.draftService=draftService;
		this.vopoFactory=vopoFactory;
		this.formatCheckService=formatCheckService;
		this.deliverDataService=CacheHelper.getDeliverDataService();
	}
	/* (non-Javadoc)
	 * @see bl.blService.FormBLService#newID()
	 */
	public String newID() {
		String unitID=UserInfo.getInstitutionID();
		try {
			String ID=deliverDataService.newID(unitID);
			return ID;
		} catch (RemoteException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.deliverblService.DeliverBLService#checkFormat(vo.delivervo.DeliverVO, boolean)
	 */
	public ArrayList<CheckFormMessage> checkFormat(DeliverVO form,
			boolean isFinal) {
		// TODO Auto-generated method stub
		return new ArrayList<CheckFormMessage>();
	}

	/* (non-Javadoc)
	 * @see bl.blService.deliverblService.DeliverBLService#submit(vo.delivervo.DeliverVO)
	 */
	public OperationMessage submit(DeliverVO form) {
		try {
			DeliverPO po=(DeliverPO)vopoFactory.transVOtoPO(form);
			return CacheHelper.getExamineSubmitService().submit(po);
		} catch (Exception e) {
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.deliverblService.DeliverBLService#saveDraft(vo.delivervo.DeliverVO)
	 */
	public OperationMessage saveDraft(DeliverVO form) {
		return draftService.saveDraft(form);
	}

	/* (non-Javadoc)
	 * @see bl.blService.deliverblService.DeliverBLService#loadDraft()
	 */
	public DeliverVO loadDraft() {
		return (DeliverVO)draftService.getDraft(FormEnum.DELIVER);
	}

	/* (non-Javadoc)
	 * @see bl.blService.deliverblService.DeliverBLService#getOrderVO(java.lang.String)
	 */
	public OrderVO getOrderVO(String orderID) {
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
	 * @see bl.blService.deliverblService.DeliverBLService#getUnhandledOrders(java.lang.String)
	 */
	@Override
	public ArrayList<String> getUnhandledOrderID(String hallID) {
		try {
			ArrayList<String> order=deliverDataService.available(hallID);
			return order;
		} catch (RemoteException e) {
			return null;
		}
	}

}
