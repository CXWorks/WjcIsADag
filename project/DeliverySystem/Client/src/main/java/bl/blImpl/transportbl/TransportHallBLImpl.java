package bl.blImpl.transportbl;

import java.util.ArrayList;

import message.CheckFormMessage;
import message.OperationMessage;
import po.transportdata.LoadPO;
import vo.managevo.car.CarVO;
import vo.managevo.staff.StaffVO;
import vo.ordervo.OrderVO;
import vo.transitvo.LoadVO;
import bl.blService.transportblService.TransportHallBLService;

public class TransportHallBLImpl implements TransportHallBLService {

	
		
		public LoadVO loadDraft() {
			// TODO Auto-generated method stub
			return new LoadVO();
		}
		public OperationMessage saveDraft(LoadVO form) {
			// TODO Auto-generated method stub
			return new OperationMessage ();
		}
		public ArrayList<CheckFormMessage> checkFormat(LoadVO form,
				boolean isFinal) {
			// TODO Auto-generated method stub
			ArrayList<CheckFormMessage> result=new ArrayList<CheckFormMessage>();
			CheckFormMessage stub=new CheckFormMessage();
			result.add(stub);
			return result;

		}
		public OperationMessage submit(LoadVO form) {
			// TODO Auto-generated method stub
			return new OperationMessage();
		}
		/* (non-Javadoc)
		 * @see bl.blService.FormBLService#newID()
		 */
		public String newID() {
			// TODO Auto-generated method stub
			return null;
		}
		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportHallBLService#getDrivers(java.lang.String)
		 */
		public ArrayList<StaffVO> getDrivers(String hallID) {
			// TODO Auto-generated method stub
			return null;
		}
		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportHallBLService#getCars(java.lang.String)
		 */
		public ArrayList<CarVO> getCars(String hallID) {
			// TODO Auto-generated method stub
			return null;
		}
		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportHallBLService#getLocation(java.lang.String)
		 */
		public ArrayList<Object> getLocation(String hallID) {
			// TODO Auto-generated method stub
			return null;
		}
		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportHallBLService#getOrder(java.lang.String)
		 */
		public OrderVO getOrder(String orderID) {
			// TODO Auto-generated method stub
			return null;
		}
		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportHallBLService#checkFormat(po.transportdata.LoadPO)
		 */
		public ArrayList<CheckFormMessage> checkFormat(LoadPO form) {
			// TODO Auto-generated method stub
			return null;
		}
		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportHallBLService#submit(po.transportdata.LoadPO)
		 */
		public OperationMessage submit(LoadPO form) {
			// TODO Auto-generated method stub
			return null;
		}
		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportHallBLService#saveDraft()
		 */
		public OperationMessage saveDraft() {
			// TODO Auto-generated method stub
			return null;
		}
	

	

}
