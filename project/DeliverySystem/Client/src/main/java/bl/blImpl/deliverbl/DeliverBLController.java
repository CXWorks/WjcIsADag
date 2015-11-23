package bl.blImpl.deliverbl;

import java.util.ArrayList;

import po.FormEnum;
import po.deliverdata.DeliverPO;
import message.CheckFormMessage;
import message.OperationMessage;
import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;
import bl.blService.FormatCheckService.FormatCheckService;
import bl.blService.deliverblService.DeliverBLService;
import bl.clientNetCache.CacheHelper;
import bl.tool.draft.DraftService;
import bl.tool.vopo.VOPOFactory;

/** 
 * Client//bl.blImpl.deliverbl//DeliverBLController.java
 * @author CXWorks
 * @date 2015年11月15日 下午4:45:57
 * @version 1.0 
 */
public class DeliverBLController implements DeliverBLService {
	private Deliver deliver;
	private DraftService draftService;
	private VOPOFactory vopoFactory;
	private FormatCheckService formatCheckService;
	//
	public DeliverBLController(VOPOFactory vopoFactory,DraftService draftService,FormatCheckService formatCheckService){
		this.draftService=draftService;
		this.vopoFactory=vopoFactory;
		this.formatCheckService=formatCheckService;
	}
	/* (non-Javadoc)
	 * @see bl.blService.FormBLService#newID()
	 */
	public String newID() {
		// TODO Auto-generated method stub
		return "2333";
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
			return CacheHelper.getDeliverDataService().insert(po);
		} catch (Exception e) {
			// TODO: handle exception
			return new OperationMessage(false, "");
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
		// TODO Auto-generated method stub
		return new OrderVO("1123000001");
	}

}
