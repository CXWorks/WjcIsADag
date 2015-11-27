package bl.blService.financeblService;

import bl.blService.FormBLService;
import vo.financevo.PaymentVO;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface PaymentBLService extends FormBLService<PaymentVO>{

    public String getNewPaymentID (String date);

    public PaymentVO getPaymentVO(String paymentID);

    public List<PaymentVO> getPaymentVOs(Calendar startDate, Calendar endDate);

}
