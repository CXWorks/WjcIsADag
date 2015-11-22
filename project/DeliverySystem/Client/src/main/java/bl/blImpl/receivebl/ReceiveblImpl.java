package bl.blImpl.receivebl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import message.CheckFormMessage;
import message.OperationMessage;
import po.FormEnum;
import po.receivedata.ReceivePO;
import vo.FormVO;
import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;
import vo.receivevo.ReceiveVO;
import vo.transitvo.CenterOutVO;
import vo.transitvo.LoadVO;
import vo.transitvo.TransitVO;
import bl.blService.FormatCheckService.FormatCheckService;
import bl.blService.receiveblService.ReceiveBLService;
import bl.clientNetCache.CacheHelper;
import bl.tool.draft.DraftService;
import bl.tool.vopo.VOPOFactory;

/** 
 * Client//blImpl.receivebl//ReceiveblImpl.java
 * @author wjc
 * @date 2015年10月25日 
 * @version 1.0 
 */
public class ReceiveblImpl implements ReceiveBLService {
	DraftService draftService;
	VOPOFactory vopoFactory;
	FormatCheckService formatCheckService;
	//
	public ReceiveblImpl(VOPOFactory vopoFactory,DraftService draftService,FormatCheckService formatCheckService){
		this.draftService=draftService;
		this.vopoFactory=vopoFactory;
		this.formatCheckService=formatCheckService;
	}
	
	

	public ArrayList<CheckFormMessage> checkFormat(ReceiveVO form,
			boolean isFinal) {
		ArrayList<CheckFormMessage> result =new ArrayList<CheckFormMessage>();
		//orderID
		if (form.getOrderID()!=null) {
			result.add(formatCheckService.checkOrderID(form.getOrderID()));
		} else {
			if (isFinal) {
				result.add(new CheckFormMessage(false, "订单号为空"));
			} else {
				result.add(new CheckFormMessage());
			}
		}
		//transitID
		if (form.getTransitID()!=null) {
			result.add(formatCheckService.checkTransitID(form.getTransitID()));
		} else {
			if (isFinal) {
				result.add(new CheckFormMessage(false, "中转单号为空"));
			} else {
				result.add(new CheckFormMessage());
			}
		}
		//date
		if (form.getDate()!=null) {
			result.add(formatCheckService.checkPreDate(form.getDate()));
		} else {
			if (isFinal) {
				result.add(new CheckFormMessage(false, "到达日期为空"));
			} else {
				result.add(new CheckFormMessage());
			}
		}
		return result;
	}

	public OperationMessage submit(ReceiveVO form) {
		try {
			return CacheHelper.getReceiveDataService().insert((ReceivePO)vopoFactory.transVOtoPO(form));
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
		if (formatCheckService.checkOrderID(orderID).getCheckResult()) {
			return new OrderVO();
		}
		return new OrderVO();
	}

	public TransitVO getTransitVO() {
		return new CenterOutVO();
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormBLService#newID()
	 */
	public String newID() {
		try {
			return CacheHelper.getReceiveDataService().newID();
		} catch (RemoteException e) {
			return null;
		}
	}


}
