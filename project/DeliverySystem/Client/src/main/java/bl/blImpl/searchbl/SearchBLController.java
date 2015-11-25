package bl.blImpl.searchbl;

import message.CheckFormMessage;
import vo.logisticsvo.LogisticsVO;
import bl.blService.searchblService.SearchBLService;

public class SearchBLController implements SearchBLService{
	private OrderHelper orderHelper;
	public SearchBLController(){
		this.orderHelper=new OrderHelper();
	}

	public CheckFormMessage checkFormat(String orderID) {
		// TODO Auto-generated method stub
		return new CheckFormMessage();
	}

	public LogisticsVO searchOrder(String orderID) {
		return orderHelper.searchOrder(orderID);
	}

}
