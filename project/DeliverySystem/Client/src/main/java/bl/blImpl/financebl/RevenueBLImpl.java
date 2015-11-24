package bl.blImpl.financebl;

import bl.blService.financeblService.RevenueBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import util.R;
import vo.financevo.RevenueVO;
import vo.ordervo.OrderVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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
        return new OrderVO("1123000001");
    }

    public RevenueVO getRevenueVO(String revenueID) {
        return new RevenueVO("020011002201511230000001");
    }

    public RevenueVO getRevenueVO(String date, String hallID) {
        return new RevenueVO("020011002201511230000001");
    }

    public List<RevenueVO> getRevenueVOs(String startDate, String endDate) {
        return new LinkedList<RevenueVO>();
    }

    public RevenueVO loadDraft() {
        return new RevenueVO("020011002201511230000001");
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

	/* (non-Javadoc)
	 * @see bl.blService.FormBLService#newID()
	 */
	public String newID() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.financeblService.RevenueBLService#sum(java.lang.String)
	 */
	public double sum(String revenueID) {
		// TODO Auto-generated method stub
		return 0;
	}
}
