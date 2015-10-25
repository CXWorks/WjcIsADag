package blImpl.financebl;

import blService.financeblService.PaymentBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import vo.financevo.PaymentVO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/26.
 */
public class PaymentBLImpl implements PaymentBLService {
    public String getNewPaymentID(String date) {
        return "222333";
    }

    public PaymentVO getPaymentVO(String paymentID) {
        return new PaymentVO();
    }

    public List<PaymentVO> getPaymentVOs(String startDate, String endDate) {
        return new LinkedList<PaymentVO>();
    }

    public PaymentVO loadDraft() {
        return new PaymentVO();
    }

    public OperationMessage saveDraft(PaymentVO form) {
        return new OperationMessage();
    }

    public List<CheckFormMessage> checkFormat(PaymentVO form, boolean isFinal) {
        return new LinkedList<CheckFormMessage>();
    }

    public OperationMessage submit(PaymentVO form) {
        return new OperationMessage();
    }
}
