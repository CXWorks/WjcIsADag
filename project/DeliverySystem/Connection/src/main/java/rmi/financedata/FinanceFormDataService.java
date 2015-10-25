package rmi.financedata;

import po.financedata.PaymentPO;
import po.financedata.RevenuePO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Sissel on 2015/10/23.
 */
public interface FinanceFormDataService extends Remote{

    public String getNewRevenueID(String date, String hallID) throws RemoteException;

    public String getNewPaymentID(String date) throws RemoteException;

    public RevenuePO getRevenuePO(String formID) throws RemoteException;

    public PaymentPO getPaymentPO(String formID) throws RemoteException;

    public List<RevenuePO> updateRevenuePOs(String staffID) throws RemoteException;

    public List<PaymentPO> updatePaymentPOs(String staffID) throws RemoteException;

    public List<PaymentPO> downloadAllPaymentPOs() throws RemoteException;

    public List<RevenuePO> downloadAllRevenuePOs() throws RemoteException;

}