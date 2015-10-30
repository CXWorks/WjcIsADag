package bl.blImpl.transportbl;

import java.util.ArrayList;
import java.util.List;

import message.CheckFormMessage;
import message.OperationMessage;
import po.transportdata.CenterOutPO;
import vo.FormVO;
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
		


}
