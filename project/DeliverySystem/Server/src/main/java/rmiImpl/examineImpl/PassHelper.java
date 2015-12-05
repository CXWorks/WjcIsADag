package rmiImpl.examineImpl;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import message.OperationMessage;
import po.FormEnum;
import po.deliverdata.DeliverPO;
import po.financedata.PaymentPO;
import po.financedata.RevenuePO;
import po.orderdata.OrderPO;
import po.receivedata.ReceivePO;
import po.storedata.StoreInPO;
import po.storedata.StoreOutPO;
import po.transportdata.CenterOutPO;
import po.transportdata.LoadPO;
import rmi.DataService;
import rmi.deliverdata.DeliverDataService;
import rmi.financedata.PaymentDataService;
import rmi.financedata.RevenueDataService;
import rmi.orderdata.OrderDataService;
import rmi.receivedata.ReceiveDataService;
import rmi.storedata.StoreFormDataService;
import rmi.storedata.StoreModelDataService;
import rmi.transportdata.CenterOutDataService;
import rmi.transportdata.LoadDataService;
import rmiImpl.deliverdata.DeliverDataImpl;
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

}
