package rmiImpl.examineImpl;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import rmi.deliverdata.DeliverDataService;
import rmi.financedata.PaymentDataService;
import rmi.financedata.RevenueDataService;
import rmi.orderdata.OrderDataService;
import rmi.receivedata.ReceiveDataService;
import rmi.storedata.StoreFormDataService;
import rmi.transportdata.CenterOutDataService;
import rmi.transportdata.LoadDataService;
import rmiImpl.deliverdata.DeliverDataImpl;
import rmiImpl.financedata.PaymentDataImpl;
import rmiImpl.financedata.RevenueDataImpl;
import rmiImpl.orderdata.OrderDataImpl;
import rmiImpl.receivedata.ReceiveDataImpl;
import rmiImpl.storedata.StoreFormDataImpl;
import rmiImpl.transportdata.CenterOutDataImpl;
import rmiImpl.transportdata.LoadDataImpl;

public class PassHelper {
	private static OrderDataService orderDataService;
	private static DeliverDataService deliverDataService;
	private static PaymentDataService paymentDataService;
	private static RevenueDataService revenueDataService;
	private static ReceiveDataService receiveDataService;
	private static StoreFormDataService storeFormDataService;
	private static CenterOutDataService transportDataService;
	private static LoadDataService loadDataService;
	
	public PassHelper() throws RemoteException, MalformedURLException{
		orderDataService = new OrderDataImpl();
		deliverDataService = new DeliverDataImpl();
		paymentDataService = new PaymentDataImpl();
		revenueDataService = new RevenueDataImpl();
		receiveDataService = new ReceiveDataImpl();
		storeFormDataService = new StoreFormDataImpl();
		transportDataService = new CenterOutDataImpl();
		loadDataService = new LoadDataImpl();
	}

	public static OrderDataService getOrderDataService() {
		return orderDataService;
	}

	public static DeliverDataService getDeliverDataService() {
		return deliverDataService;
	}

	public static PaymentDataService getPaymentDataService() {
		return paymentDataService;
	}

	public static RevenueDataService getRevenueDataService() {
		return revenueDataService;
	}

	public static ReceiveDataService getReceiveDataService() {
		return receiveDataService;
	}

	public static StoreFormDataService getStoreFormDataService() {
		return storeFormDataService;
	}

	public static CenterOutDataService getTransportDataService() {
		return transportDataService;
	}

	public static LoadDataService getLoadDataService() {
		return loadDataService;
	}
	
	
}
