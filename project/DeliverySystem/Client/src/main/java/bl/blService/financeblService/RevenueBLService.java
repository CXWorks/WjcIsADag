package bl.blService.financeblService;

import bl.blService.FormBLService;
import message.OperationMessage;
import vo.financevo.RevenueVO;
import vo.ordervo.OrderVO;

import java.util.List;

/**
 * Created by Sissel on 2015/10/24.
 */
public interface RevenueBLService extends FormBLService<RevenueVO> {

    public OperationMessage loadOrder(String orderNumber);

    public String getNewRevenueID(String date);

    public OrderVO getOrderVO();

    public RevenueVO getRevenueVO(String revenueID);

    public RevenueVO getRevenueVO(String date, String hallID);

    public List<RevenueVO> getRevenueVOs(String startDate, String endDate);

}
