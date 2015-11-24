package bl.blImpl.deliverbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.deliverdata.DeliverPO;
import rmi.deliverdata.DeliverDataService;
import message.OperationMessage;
import vo.delivervo.DeliverVO;
import bl.blService.deliverblService.CheckDeliverForm;
import bl.clientNetCache.CacheHelper;
import bl.tool.vopo.VOPOFactory;

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
	 * @see bl.blService.deliverblService.CheckDeliverForm#finishDelivery(java.util.ArrayList)
	 */
	public OperationMessage finishDelivery(ArrayList<DeliverVO> finfished) {
		DeliverPO po;
		for (int i = 0; i < finfished.size(); i++) {
			DeliverVO each=finfished.get(i);
			po=(DeliverPO)vopoFactory.transVOtoPO(each);
			po.setFinished(true);
			try {
				OperationMessage op=deliverDataService.update(po);
				if (!op.operationResult) {
					return op;
				}
			} catch (RemoteException e) {
				return new OperationMessage(false, "net error");
			}
		}
		//
		return new OperationMessage();
	}

}
