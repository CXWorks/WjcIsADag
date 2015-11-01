package rmi.financedata;

import po.CommonPO;
import po.financedata.PaymentPO;
import po.financedata.RevenuePO;
import rmi.DataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/23.
 */
public interface FinanceFormDataService extends DataService<CommonPO>{
	
    public String getNewRevenueID(String date, String hallID) throws RemoteException;

    public String getNewPaymentID(String date) throws RemoteException;

    public RevenuePO getRevenuePO(String formID) throws RemoteException;

    public PaymentPO getPaymentPO(String formID) throws RemoteException;

    public LinkedList<RevenuePO> updateRevenuePOs(String staffID) throws RemoteException;

    public LinkedList<PaymentPO> updatePaymentPOs(String staffID) throws RemoteException;

    public LinkedList<PaymentPO> downloadAllPaymentPOs() throws RemoteException;

    public LinkedList<RevenuePO> downloadAllRevenuePOs() throws RemoteException;

}
