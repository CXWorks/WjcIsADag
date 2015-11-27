package bl.blService.financeblService;

import bl.blService.FormBLService;
import message.OperationMessage;
import vo.financevo.RevenueVO;
import vo.ordervo.OrderVO;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Sissel on 2015/10/24.
 */
public interface RevenueBLService extends FormBLService<RevenueVO> {

    public OrderVO loadOrder(String orderNumber);

    public String getNewRevenueID(String date);

    public RevenueVO getRevenueVO(String revenueID);

    public RevenueVO getRevenueVO(String date, String hallID);

    public List<RevenueVO> getRevenueVOs(Calendar startDate, Calendar endDate);
    
    public double sum(String revenueID);

}
