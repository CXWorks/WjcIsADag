package blImpl.financebl;

import blService.financeblService.RevenueBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import vo.financevo.RevenueVO;
import vo.ordervo.OrderVO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/26.
 */
public class RevenueBLImpl implements RevenueBLService {
    public OperationMessage loadOrder(String orderNumber) {
        return new OperationMessage();
    }

    public String getNewRevenueID(String date) {
        return "222333";
    }

    public OrderVO getOrderVO() {
        return new OrderVO();
    }

    public RevenueVO getRevenueVO(String revenueID) {
        return new RevenueVO();
    }

    public RevenueVO getRevenueVO(String date, String hallID) {
        return new RevenueVO();
    }

    public List<RevenueVO> getRevenueVOs(String startDate, String endDate) {
        return new LinkedList<RevenueVO>();
    }

    public RevenueVO loadDraft() {
        return new RevenueVO();
    }

    public OperationMessage saveDraft(RevenueVO form) {
        return new OperationMessage();
    }

    public ArrayList<CheckFormMessage> checkFormat(RevenueVO form, boolean isFinal) {
    	ArrayList<CheckFormMessage> result =new ArrayList<CheckFormMessage>();
		CheckFormMessage stub=new CheckFormMessage();
		result.add(stub);
		return result;
    }

    public OperationMessage submit(RevenueVO form) {
        return new OperationMessage();
    }
}
