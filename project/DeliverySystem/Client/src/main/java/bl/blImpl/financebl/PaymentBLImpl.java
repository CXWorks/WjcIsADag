package bl.blImpl.financebl;

import bl.blService.financeblService.PaymentBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import rmi.financedata.FinanceFormDataService;
import util.R;
import vo.financevo.PaymentVO;

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

    public ArrayList<CheckFormMessage> checkFormat(PaymentVO form, boolean isFinal) {
    	ArrayList<CheckFormMessage> result =new ArrayList<CheckFormMessage>();
		CheckFormMessage stub=new CheckFormMessage();
		result.add(stub);
		return result;
    }

    public OperationMessage submit(PaymentVO form) {
        return new OperationMessage();
    }

    public static void drive(FinanceFormDataService ffds) throws RemoteException {
        if(ffds.downloadAllPaymentPOs() != null)
            System.out.println("downloadAllPaymentPOs tested");
        if(ffds.getNewPaymentID("222333") != null)
            System.out.println("getNewPaymentID tested");
        if(ffds.getPaymentPO("222333") != null)
            System.out.println("getPaymentPO tested");
        if(ffds.updatePaymentPOs("222333") != null)
            System.out.println("updatePaymentPOs tested");
    }

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        FinanceFormDataService ffds = (FinanceFormDataService) Naming.lookup
                ("rmi://" + R.string.LocalHost + "/" + R.string.FinanceDataService);
        drive(ffds);
    }
}
