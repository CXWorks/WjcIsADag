package rmiImpl.examineImpl;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import rmi.deliverdata.DeliverDataService;
import rmi.financedata.BankAccountDataService;
import rmi.financedata.PaymentDataService;
import rmi.financedata.RevenueDataService;
import rmi.orderdata.OrderDataService;
import rmi.receivedata.ReceiveDataService;
import rmi.storedata.StoreFormDataService;
import rmi.storedata.StoreModelDataService;
import rmi.transportdata.CenterOutDataService;
import rmi.transportdata.LoadDataService;
import rmiImpl.deliverdata.DeliverDataImpl;
import rmiImpl.financedata.BankAccountDataImpl;
import rmiImpl.financedata.PaymentDataImpl;
import rmiImpl.financedata.RevenueDataImpl;
import rmiImpl.orderdata.OrderDataImpl;
import rmiImpl.receivedata.ReceiveDataImpl;
import rmiImpl.storedata.StoreFormDataImpl;
import rmiImpl.storedata.StoreModelDataImpl;
import rmiImpl.transportdata.CenterOutDataImpl;
import rmiImpl.transportdata.LoadDataImpl;

public class PassHelper {
	private static OrderDataService orderDataService;
	private static DeliverDataService deliverDataService;
	private static PaymentDataService paymentDataService;
	private static RevenueDataService revenueDataService;
	private static ReceiveDataService receiveDataService;
	private static StoreFormDataService storeFormDataService;
	private static StoreModelDataService storeModelDataService;
	private static CenterOutDataService transportDataService;
	private static LoadDataService loadDataService;
	private static BankAccountDataService bankAccountDataService;

	public PassHelper() throws RemoteException, MalformedURLException{
		orderDataService = new OrderDataImpl();
		deliverDataService = new DeliverDataImpl();
		paymentDataService = new PaymentDataImpl();
		revenueDataService = new RevenueDataImpl();
		receiveDataService = new ReceiveDataImpl();
		storeFormDataService = new StoreFormDataImpl();
		storeModelDataService = new StoreModelDataImpl();
		transportDataService = new CenterOutDataImpl();
		loadDataService = new LoadDataImpl();
		bankAccountDataService = new BankAccountDataImpl();
	}

	public OrderDataService getOrderDataService() {
		return orderDataService;
	}

	public DeliverDataService getDeliverDataService() {
		return deliverDataService;
	}

	public PaymentDataService getPaymentDataService() {
		return paymentDataService;
	}

	public RevenueDataService getRevenueDataService() {
		return revenueDataService;
	}

	public ReceiveDataService getReceiveDataService() {
		return receiveDataService;
	}

	public StoreFormDataService getStoreFormDataService() {
		return storeFormDataService;
	}

	public StoreModelDataService getStoreModelDataService() {
		return storeModelDataService;
	}

	public CenterOutDataService getTransportDataService() {
		return transportDataService;
	}

	public LoadDataService getLoadDataService() {
		return loadDataService;
	}

	public BankAccountDataService getBankAccountDataService() {
		return bankAccountDataService;
	}

}
