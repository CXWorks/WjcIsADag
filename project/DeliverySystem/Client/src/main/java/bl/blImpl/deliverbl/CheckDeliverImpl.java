package bl.blImpl.deliverbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.deliverdata.DeliverPO;
import po.orderdata.OrderPO;
import rmi.deliverdata.DeliverDataService;
import rmi.orderdata.OrderDataService;
import message.OperationMessage;
import vo.delivervo.DeliverVO;
import bl.blService.deliverblService.CheckDeliverForm;
import bl.clientNetCache.CacheHelper;
import tool.vopo.VOPOFactory;

/** 
 * Client//bl.blImpl.deliverbl//CheckDeliverImpl.java
 * @author CXWorks
 * @date 2015年11月15日 下午4:52:45
 * @version 1.0 
 */
public class CheckDeliverImpl implements CheckDeliverForm {
	private DeliverDataService deliverDataService;
	private VOPOFactory vopoFactory;
	
	public CheckDeliverImpl(VOPOFactory vopoFactory){
		this.deliverDataService=CacheHelper.getDeliverDataService();
		this.vopoFactory=vopoFactory;
	}
	/* (non-Javadoc)
	 * @see bl.blService.deliverblService.CheckDeliverForm#getDeliverForms(java.lang.String)
	 */
	public ArrayList<DeliverVO> getDeliverForms(String postmanID) {
		try {
			ArrayList<String> ID=deliverDataService.searchAsPerson(postmanID);
			ArrayList<DeliverVO> vo=new ArrayList<DeliverVO>(ID.size());
			for(int i=0;i<ID.size();i++){
				DeliverPO po=deliverDataService.getFormPO(ID.get(i));
				DeliverVO temp=(DeliverVO)vopoFactory.transPOtoVO(po);
				vo.add(temp);
			}
			return vo;
		} catch (RemoteException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.deliverblService.CheckDeliverForm#finishDelivery(vo.DeliverVO)
	 */
	public OperationMessage finishDelivery(DeliverVO each) {
		DeliverPO po;
		
			po=(DeliverPO)vopoFactory.transVOtoPO(each);
			po.setFinished(true);
			try {
				OperationMessage op=deliverDataService.update(po);
				OrderDataService orderDataService=CacheHelper.getOrderDataService();
				OrderPO orderPO=orderDataService.getFormPO(each.getOrderID());
				orderPO.finfished(each.getReceiveDate()	, each.getReceivePeople());
				orderDataService.update(orderPO);
			} catch (RemoteException e) {
				return new OperationMessage(false, "net error");
			}
		
		//
		return new OperationMessage();
	}

}
