package blImpl.orderbl;

import java.util.ArrayList;

import message.CheckFormMessage;
import message.OperationMessage;
import po.orderdata.OrderPO;
import vo.ordervo.OrderVO;
import vo.ordervo.PredictVO;
import blService.orderblService.OrderBLService;

public class OrderBLImpl implements OrderBLService{

	public OrderVO loadDraft() {
		// TODO Auto-generated method stub
		return new OrderVO();
	}

	public OperationMessage saveDraft(OrderVO form) {
		// TODO Auto-generated method stub
		return new  OperationMessage();
	}

	public ArrayList<CheckFormMessage> checkFormat(OrderVO form, boolean isFinal) {
		// TODO Auto-generated method stub
		ArrayList<CheckFormMessage> result=new ArrayList<CheckFormMessage>();
		CheckFormMessage stub=new CheckFormMessage();
		result.add(stub);
		return result;
	}

	public OperationMessage submit(OrderVO form) {
		// TODO Auto-generated method stub
		return new  OperationMessage();
	}

	public PredictVO predict(OrderPO po) {
		// TODO Auto-generated method stub
		return new PredictVO();
	}

}
