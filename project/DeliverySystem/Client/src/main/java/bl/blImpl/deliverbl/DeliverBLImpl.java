package bl.blImpl.deliverbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.FormEnum;
import po.deliverdata.DeliverPO;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import po.orderdata.OrderPO;
import rmi.deliverdata.DeliverDataService;
import rmi.memberdata.MemberDataService;
import rmi.orderdata.OrderDataService;
import message.CheckFormMessage;
import message.OperationMessage;
import userinfo.UserInfo;
import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;
import bl.NetReconnect.Reconnect;
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
	//
	public DeliverBLImpl(VOPOFactory vopoFactory,DraftService draftService){
		this.draftService=draftService;
		this.vopoFactory=vopoFactory;
		
	}
	/* (non-Javadoc)
	 * @see bl.blService.FormBLService#newID()
	 */
	public String newID() {
		DeliverDataService deliverDataService=CacheHelper.getDeliverDataService();
		String unitID=UserInfo.getInstitutionID();
		try {
			String ID=deliverDataService.newID(unitID);
			return ID;
		} catch (RemoteException e) {
			Reconnect reconnect=new Reconnect();
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
		} catch (RemoteException e) {
			Reconnect reconnect=new Reconnect();
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
			Reconnect reconnect=new Reconnect();
			return null;
		}
	}
	/* (non-Javadoc)
	 * @see bl.blService.deliverblService.DeliverBLService#getUnhandledOrders(java.lang.String)
	 */
	@Override
	public ArrayList<String> getUnhandledOrderID(String hallID) {
		DeliverDataService deliverDataService=CacheHelper.getDeliverDataService();
		try {
			ArrayList<String> order=deliverDataService.available(hallID);
			return order;
		} catch (RemoteException e) {
			Reconnect reconnect=new Reconnect();
			return null;
		}
	}
	@Override
	public ArrayList<String> getPostman(String hallID) {
		// TODO Auto-generated method stub
		MemberDataService<StaffPO> memberDataService=CacheHelper.getMemberDataService_staff();
		try {
			ArrayList<StaffPO> po=memberDataService.getStaffByInstitution(hallID);
			ArrayList<String> post=new ArrayList<String>();
			for (StaffPO staffPO : po) {
				if (staffPO.getStaff()==StaffTypeEnum.DELIVER) {
					post.add(staffPO.getID());
				}
			}
			return post;
		} catch (RemoteException e) {
			Reconnect reconnect=new Reconnect();
			return null;
		}

	}

}
