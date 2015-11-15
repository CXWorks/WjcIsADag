package bl.blImpl.transportbl;

import java.util.ArrayList;
import java.util.List;

import message.CheckFormMessage;
import message.OperationMessage;
import po.transportdata.CenterOutPO;
import vo.FormVO;
import vo.ordervo.OrderVO;
import vo.transitvo.CenterOutVO;
import bl.blService.transportblService.TransportCenterBLService;
import bl.blService.transportblService.TransportHallBLService;

public class TransportCenterBLImpl implements TransportCenterBLService {

		
		public CenterOutVO loadDraft() {
			// TODO Auto-generated method stub
			return new CenterOutVO();
		}
		public OperationMessage saveDraft(CenterOutVO form) {
			// TODO Auto-generated method stub
			return new OperationMessage();
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
			// TODO Auto-generated method stub
			return new OperationMessage();
		}
		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportCenterBLService#checkFormat(po.transportdata.CenterOutPO)
		 */
		public ArrayList<CheckFormMessage> checkFormat(CenterOutPO form) {
			// TODO Auto-generated method stub
			return null;
		}
		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportCenterBLService#submit(po.transportdata.CenterOutPO)
		 */
		public OperationMessage submit(CenterOutPO form) {
			// TODO Auto-generated method stub
			return null;
		}
		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportCenterBLService#saveDraft()
		 */
		public OperationMessage saveDraft() {
			// TODO Auto-generated method stub
			return null;
		}
		/* (non-Javadoc)
		 * @see bl.blService.FormBLService#newID()
		 */
		public String newID() {
			// TODO Auto-generated method stub
			return null;
		}
		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportCenterBLService#getOrder(java.lang.String)
		 */
		public OrderVO getOrder(String orderID) {
			// TODO Auto-generated method stub
			return null;
		}
		


}
