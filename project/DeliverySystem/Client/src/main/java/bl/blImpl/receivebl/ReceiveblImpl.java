package bl.blImpl.receivebl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

import message.CheckFormMessage;
import message.OperationMessage;
import po.FormEnum;
import po.orderdata.OrderPO;
import po.receivedata.ReceivePO;
import rmi.orderdata.OrderDataService;
import rmi.receivedata.ReceiveDataService;
import userinfo.UserInfo;
import vo.ordervo.OrderVO;
import vo.receivevo.ReceiveVO;
import vo.transitvo.CenterOutVO;
import vo.transitvo.TransitVO;
import bl.blService.FormatCheckService.FormatCheckService;
import bl.blService.receiveblService.ReceiveBLService;
import bl.clientNetCache.CacheHelper;
import tool.draft.DraftService;
import tool.vopo.VOPOFactory;

/** 
 * Client//blImpl.receivebl//ReceiveblImpl.java
 * @author wjc
 * @date 2015年10月25日 
 * @version 1.0 
 */
public class ReceiveblImpl implements ReceiveBLService {
	private DraftService draftService;
	private VOPOFactory vopoFactory;
	private FormatCheckService formatCheckService;
	//
	public ReceiveblImpl(VOPOFactory vopoFactory,DraftService draftService,FormatCheckService formatCheckService){
		this.draftService=draftService;
		this.vopoFactory=vopoFactory;
		this.formatCheckService=formatCheckService;
	}

	

	public OperationMessage submit(ReceiveVO form) {
		try {
			ReceivePO temp=(ReceivePO)vopoFactory.transVOtoPO(form);
			ReceiveDataService receiveDataService=CacheHelper.getReceiveDataService();
			return receiveDataService.insert(temp);
		} catch (RemoteException e) {
			return new OperationMessage(false,"net error");
		}
	}

	public OperationMessage saveDraft(ReceiveVO form) {
		return draftService.saveDraft(form);
	}

	public ReceiveVO loadDraft() {
		return (ReceiveVO) draftService.getDraft(FormEnum.RECEIVE);
	}

	public OrderVO getOrderVO(String orderID) {
			OrderDataService orderDataService=CacheHelper.getOrderDataService();
			OrderPO po;
			try {
				po = orderDataService.getFormPO(orderID);
				OrderVO vo=(OrderVO)vopoFactory.transPOtoVO(po);
				return vo;
			} catch (RemoteException e) {
				return null;
			}
			
		
	}

	public TransitVO getTransitVO() {
		return new CenterOutVO("070010001201511230000001");
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormBLService#newID()
	 */
	public String newID() {
		try {
			return CacheHelper.getReceiveDataService().newID(UserInfo.getInstitutionID());
		} catch (RemoteException e) {
			return null;
		}
	}
	/* (non-Javadoc)
	 * @see bl.blService.receiveblService.ReceiveBLService#checkOrderID(java.lang.String, boolean)
	 */
	@Override
	public CheckFormMessage checkOrderID(String orderID, boolean isFinal) {
		return null;
	}



	/* (non-Javadoc)
	 * @see bl.blService.receiveblService.ReceiveBLService#checkTransitID(java.lang.String, boolean)
	 */
	@Override
	public CheckFormMessage checkTransitID(String transitID, boolean isFinal) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see bl.blService.receiveblService.ReceiveBLService#checkDate(java.util.Calendar, boolean)
	 */
	@Override
	public CheckFormMessage checkDate(Calendar date, boolean isFinal) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see bl.blService.FormBLService#checkFormat(vo.FormVO, boolean)
	 */
	@Override
	public ArrayList<CheckFormMessage> checkFormat(ReceiveVO form,
			boolean isFinal) {
		// TODO Auto-generated method stub
		return null;
	}


}
