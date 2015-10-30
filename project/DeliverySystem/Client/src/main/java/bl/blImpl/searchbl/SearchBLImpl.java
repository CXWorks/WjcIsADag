package bl.blImpl.searchbl;

import message.CheckFormMessage;
import vo.logisticsvo.LogisticsVO;
import bl.blService.searchblService.SearchBLService;

public class SearchBLImpl implements SearchBLService{
	

	public CheckFormMessage checkFormat(String orderID) {
		// TODO Auto-generated method stub
		return new CheckFormMessage();
	}

	public LogisticsVO searchOrder(String orderID) {
		// TODO Auto-generated method stub
		return new LogisticsVO();
	}

}
