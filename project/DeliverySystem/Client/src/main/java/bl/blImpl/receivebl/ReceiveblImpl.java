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
	

	public ArrayList<CheckFormMessage> checkFormat(ReceiveVO form,
			boolean isFinal) {
		// TODO Auto-generated method stub
		ArrayList<CheckFormMessage> result =new ArrayList<CheckFormMessage>();
		CheckFormMessage stub=new CheckFormMessage();
		result.add(stub);
		return result;
	}

	public OperationMessage submit(ReceiveVO form) {
		// TODO Auto-generated method stub
		try {
			return CacheHelper.getReceiveDataService().insert((ReceivePO)vopoFactory.transVOtoPO(form));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
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
		// TODO Auto-generated method stub
		return new OrderVO();
	}

	public TransitVO getTransitVO() {
		// TODO Auto-generated method stub
		return new CenterOutVO();
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormBLService#newID()
	 */
	public String newID() {
		// TODO Auto-generated method stub
		return "111111";
	}


}
