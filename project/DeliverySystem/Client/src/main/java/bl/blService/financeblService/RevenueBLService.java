package bl.blService.financeblService;

import bl.blService.FormBLService;
import message.OperationMessage;
import vo.financevo.RevenueVO;
import vo.managevo.staff.StaffVO;
import vo.ordervo.OrderVO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Sissel on 2015/10/24.
 */
public interface RevenueBLService extends FormBLService<RevenueVO> {

    public List<OrderVO> getOrders(RevenueVO revenueVO);

    public String getNewRevenueID(Calendar date);

    public RevenueVO getRevenueVO(String revenueID);

    public List<RevenueVO> getRevenueVOs(Calendar date, String hallID);

    public List<RevenueVO> getRevenueVOs(Calendar startDate, Calendar endDate);
    
    public double sum(String revenueID);
    
    public ArrayList<StaffVO> getInstitutionDelivers();

}
