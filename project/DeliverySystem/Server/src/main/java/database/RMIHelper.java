package database;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import po.memberdata.DriverPO;
import po.memberdata.StaffPO;
import rmi.accountdata.AccountDataService;
import rmi.chatRemindService.ChatRemindService;
import rmi.companydata.CompanyDataCarService;
import rmi.companydata.CompanyDataCenterService;
import rmi.companydata.CompanyDataHallService;
import rmi.configurationdata.ConfigurationDataService;
import rmi.deliverdata.DeliverDataService;
import rmi.examineService.ExamineManageService;
import rmi.examineService.ExamineSubmitService;
import rmi.financedata.BankAccountDataService;
import rmi.financedata.PaymentDataService;
import rmi.financedata.RevenueDataService;
import rmi.memberdata.MemberDataService;
import rmi.orderdata.OrderDataService;
import rmi.receivedata.ReceiveDataService;
import rmi.storedata.StoreFormDataService;
import rmi.storedata.StoreModelDataService;
import rmi.systemdata.LogDataService;
import rmi.transportdata.CenterOutDataService;
import rmi.transportdata.LoadDataService;
import rmiImpl.accountdata.AccountDataProxy;
import rmiImpl.chatRemindImpl.ChatRemindImpl;
import rmiImpl.companydata.CompanyDataCarProxy;
import rmiImpl.companydata.CompanyDataCenterProxy;
import rmiImpl.companydata.CompanyDataHallProxy;
import rmiImpl.configurationdata.ConfigurationDataProxy;
import rmiImpl.deliverdata.DeliverDataProxy;
import rmiImpl.examineImpl.ExamineManageProxy;
import rmiImpl.examineImpl.ExamineSubmitProxy;
import rmiImpl.financedata.BankAccountDataProxy;
import rmiImpl.financedata.PaymentDataProxy;
import rmiImpl.financedata.RevenueDataProxy;
import rmiImpl.memberdata.DriverDataProxy;
import rmiImpl.memberdata.StaffDataProxy;
import rmiImpl.orderdata.OrderDataProxy;
import rmiImpl.receivedata.ReceiveDataProxy;
import rmiImpl.storedata.StoreFormDataProxy;
import rmiImpl.storedata.StoreModelDataProxy;
import rmiImpl.systemdata.LogDataProxy;
import rmiImpl.transportdata.CenterOutDataProxy;
import rmiImpl.transportdata.LoadDataProxy;

/**
 * 数据层，接口处理器
 *
 * @author wjc
 * @version 2014.10.31
 */
public class RMIHelper {
	//
	private static Remote reg = null;
	private static final String IP = "127.0.0.1:2333";
	//
	private static AccountDataService accountDataService;
	private static ChatRemindService chatRemindService;
	private static CompanyDataCarService companyDataCarService;
	private static CompanyDataCenterService companyDataCenterService;
	private static CompanyDataHallService companyDataHallService;
	private static ConfigurationDataService configurationDataService;
	private static DeliverDataService deliverDataService;
	private static ExamineManageService examineManageService;
	private static ExamineSubmitService examineSubmitService;
	private static BankAccountDataService bankAccountDataService;
	private static PaymentDataService paymentDataService;
	private static RevenueDataService revenueDataService;
//	private static InitialDataService initialDataService;
	private static MemberDataService<DriverPO> memberDataService_driver;
	private static MemberDataService<StaffPO> memberDataService_staff;
	private static OrderDataService orderDataService;
	private static ReceiveDataService receiveDataService;
	private static StoreFormDataService storeFormDataService;
	private static StoreModelDataService storeModelDataService;
	private static LogDataService logDataService;
	private static CenterOutDataService transportDataService;
	private static LoadDataService loadDataService;

	public static void createService() throws RemoteException, MalformedURLException {
		accountDataService = new AccountDataProxy();
		chatRemindService = new ChatRemindImpl();
		companyDataCarService = new CompanyDataCarProxy();
		companyDataCenterService = new CompanyDataCenterProxy();
		companyDataHallService = new CompanyDataHallProxy();
		configurationDataService = new ConfigurationDataProxy();
		deliverDataService = new DeliverDataProxy();
		examineManageService = new ExamineManageProxy();
		examineSubmitService = new ExamineSubmitProxy(examineManageService.getQueue());
		bankAccountDataService = new BankAccountDataProxy();
		paymentDataService = new PaymentDataProxy();
		revenueDataService = new RevenueDataProxy();
		//
		memberDataService_driver = new DriverDataProxy();
		memberDataService_staff = new StaffDataProxy();
		orderDataService = new OrderDataProxy();
		receiveDataService = new ReceiveDataProxy();
		storeFormDataService = new StoreFormDataProxy();
		storeModelDataService = new StoreModelDataProxy();
		logDataService = new LogDataProxy();
		transportDataService = new CenterOutDataProxy();
		loadDataService = new LoadDataProxy();
	}

	public static void initializeRMI() throws RemoteException, MalformedURLException {
		RMIHelper.createService();

		String url = "rmi://" + IP + "/";
		reg = LocateRegistry.createRegistry(2333);
		Naming.rebind(url + "AccountDataService", accountDataService);
		Naming.rebind(url + "ChatRemindService", chatRemindService);
		Naming.rebind(url + "CompanyDataCarService", companyDataCarService);
		Naming.rebind(url + "CompanyDataCenterService", companyDataCenterService);
		Naming.rebind(url + "CompanyDataHallService",companyDataHallService);
		Naming.rebind(url + "ConfigurationDataService", configurationDataService);
		Naming.rebind(url + "DeliverDataService", deliverDataService);
		Naming.rebind(url + "ExamineManageService", examineManageService);
		Naming.rebind(url + "ExamineSubmitService", examineSubmitService);
		Naming.rebind(url + "BankAccountDataService",bankAccountDataService);
		Naming.rebind(url + "PaymentDataService",paymentDataService);
		Naming.rebind(url + "RevenueDataService",revenueDataService );
//		Naming.rebind(url + "InitialDataService",initialDataService);
		Naming.rebind(url + "MemberDataService_driver",memberDataService_driver);
		Naming.rebind(url + "MemberDataService_staff", memberDataService_staff);
		Naming.rebind(url + "OrderDataService",orderDataService );
		Naming.rebind(url + "ReceiveDataService", receiveDataService);
		Naming.rebind(url + "StoreFormDataService",storeFormDataService );
		Naming.rebind(url + "StoreModelDataService",storeModelDataService);
		Naming.rebind(url + "LogDataService",logDataService);
		Naming.rebind(url + "CenterOutDataService",transportDataService);
		Naming.rebind(url + "LoadDataService",loadDataService);
	}

	public static void closeServer() throws RemoteException, MalformedURLException, NotBoundException {
		ConnecterHelper.deconnSQL();

		java.rmi.server.UnicastRemoteObject.unexportObject(reg, true);
		reg = null;
	}

	public static LogDataService getLogDataService() {
		return logDataService;
	}

}
