package blImpl.transportbl;

import java.util.ArrayList;

import message.CheckFormMessage;
import message.OperationMessage;
import po.transportdata.LoadPO;
import vo.transitvo.LoadVO;
import blService.transportblService.TransportHallBLService;

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
	

	

}
