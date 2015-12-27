package bl.blImpl.deliverbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.deliverdata.DeliverPO;
import po.orderdata.OrderPO;
import rmi.deliverdata.DeliverDataService;
import rmi.orderdata.OrderDataService;
import message.OperationMessage;
import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;
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
	private VOPOFactory vopoFactory;

	public CheckDeliverImpl(VOPOFactory vopoFactory){
		
		this.vopoFactory=vopoFactory;
	}
	/* (non-Javadoc)
	 * @see bl.blService.deliverblService.CheckDeliverForm#getDeliverForms(java.lang.String)
	 */
	public ArrayList<DeliverVO> getDeliverForms(String postmanID) {
		DeliverDataService deliverDataService=CacheHelper.getDeliverDataService();
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
	public OperationMessage finishDelivery(DeliverVO each) {
		DeliverPO po;
		DeliverDataService deliverDataService=CacheHelper.getDeliverDataService();
			po=(DeliverPO)vopoFactory.transVOtoPO(each);
			po.setFinished(true);
			try {
				deliverDataService.update(po);
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
	/* (non-Javadoc)
	 * @see bl.blService.deliverblService.CheckDeliverForm#getDeliverOrder(java.lang.String)
	 */
	@Override
	public ArrayList<OrderVO> getDeliverOrder(String postmanID) {
		DeliverDataService deliverDataService=CacheHelper.getDeliverDataService();
		try {
			OrderDataService orderDataService=CacheHelper.getOrderDataService();
			ArrayList<String> ID=deliverDataService.searchAsPerson(postmanID);
			ArrayList<OrderVO> orderVOs=new ArrayList<OrderVO>(ID.size());
			DeliverPO deliverPO;
			OrderPO orderPO;
			for (int i = 0; i < ID.size(); i++) {
				deliverPO=deliverDataService.getFormPO(ID.get(i));
				orderPO=orderDataService.getFormPO(deliverPO.getOrderID());
				OrderVO orderVO=(OrderVO)vopoFactory.transPOtoVO(orderPO);
				orderVOs.add(orderVO);
			}
			return orderVOs;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/* (non-Javadoc)
	 * @see bl.blService.deliverblService.CheckDeliverForm#finishDelivery(vo.ordervo.OrderVO)
	 */

	public OperationMessage finishDelivery(OrderVO orderVO) {
		OrderDataService orderDataService=CacheHelper.getOrderDataService();
		OrderPO orderPO=(OrderPO)vopoFactory.transVOtoPO(orderVO);
		try {
			return orderDataService.update(orderPO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
