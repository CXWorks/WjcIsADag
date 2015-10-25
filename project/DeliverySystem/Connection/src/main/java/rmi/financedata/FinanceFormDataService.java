package rmi.financedata;

import po.financedata.PaymentPO;
import po.financedata.RevenuePO;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by Sissel on 2015/10/23.
 */
public interface FinanceFormDataService extends Remote{

    public String getNewRevenueID(String date, String hallID);

    public String getNewPaymentID(String date);

    public RevenuePO getRevenuePO(String formID);

    public PaymentPO getPaymentPO(String formID);

    public List<RevenuePO> updateRevenuePOs(String staffID);

    public List<PaymentPO> updatePaymentPOs(String staffID);

    public List<PaymentPO> downloadAllPaymentPOs();

    public List<RevenuePO> downloadAllRevenuePOs();

}
