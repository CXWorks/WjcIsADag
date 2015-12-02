package rmiImpl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

import po.memberdata.DriverPO;
import po.memberdata.StaffPO;
import rmi.DataService;
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
import rmi.initialdata.InitialDataService;
import rmi.memberdata.MemberDataService;
import rmi.orderdata.OrderDataService;
import rmi.receivedata.ReceiveDataService;
import rmi.storedata.StoreFormDataService;
import rmi.storedata.StoreModelDataService;
import rmi.systemdata.LogDataService;
import rmi.systemdata.SystemDataService;
import rmi.transportdata.CenterOutDataService;
import rmi.transportdata.LoadDataService;
import rmiImpl.accountdata.AccountDataImpl;
import rmiImpl.chatRemindImpl.ChatRemindImpl;
import rmiImpl.companydata.CompanyDataCarImpl;
import rmiImpl.companydata.CompanyDataCenterImpl;
import rmiImpl.companydata.CompanyDataHallImpl;
import rmiImpl.configurationdata.ConfigurationDataImpl;
import rmiImpl.deliverdata.DeliverDataImpl;
import rmiImpl.examineImpl.ExamineManageImpl;
import rmiImpl.examineImpl.ExamineSubmitImpl;
import rmiImpl.financedata.BankAccountDataImpl;
import rmiImpl.financedata.PaymentDataImpl;
import rmiImpl.financedata.RevenueDataImpl;
import rmiImpl.initaldata.InitialDataImpl;
import rmiImpl.memberdata.DriverDataImpl;
import rmiImpl.memberdata.StaffDataImpl;
import rmiImpl.orderdata.OrderDataImpl;
import rmiImpl.receivedata.ReceiveDataImpl;
import rmiImpl.storedata.StoreFormDataImpl;
import rmiImpl.storedata.StoreModelDataImpl;
import rmiImpl.transportdata.CenterOutDataImpl;
import rmiImpl.transportdata.LoadDataImpl;

/**
 * 数据层，接口处理器
 *
 * @author wjc
 * @version 2014.10.31
 */
public class DataFactory {
	//
	private static final String IP="localhost:2333";
//	//
//	private static AccountDataService accountDataService;
//	private static ChatRemindService chatRemindService;
//	private static CompanyDataCarService companyDataCarService;
//	private static CompanyDataCenterService companyDataCenterService;
//	private static CompanyDataHallService companyDataHallService;
//	private static ConfigurationDataService configurationDataService;
//	private static DeliverDataService deliverDataService;
//	private static ExamineManageService examineManageService;
//	private static ExamineSubmitService examineSubmitService;
//	private static BankAccountDataService bankAccountDataService;
//	private static PaymentDataService paymentDataService;
//	private static RevenueDataService revenueDataService;
////	private static InitialDataService initialDataService;
//	private static MemberDataService<DriverPO> memberDataService_driver;
//	private static MemberDataService<StaffPO> memberDataService_staff;
//	private static OrderDataService orderDataService;
//	private static ReceiveDataService receiveDataService;
//	private static StoreFormDataService storeFormDataService;
//	private static StoreModelDataService storeModelDataService;
//	private static LogDataService logDataService;
//	private static CenterOutDataService transportDataService;
//	private static LoadDataService loadDataService;

	public static void initializeRMI() throws RemoteException, MalformedURLException {

		ExamineManageImpl examineManage = new ExamineManageImpl();

		String url="rmi://"+IP+"/";
		LocateRegistry.createRegistry(2333);
		Naming.rebind(url + "AccountDataService", new AccountDataImpl());
		Naming.rebind(url + "ChatRemindService", new ChatRemindImpl());
		Naming.rebind(url + "CompanyDataCarService",new CompanyDataCarImpl());
		Naming.rebind(url + "CompanyDataCenterService",new CompanyDataCenterImpl());
		Naming.rebind(url + "CompanyDataHallService",new CompanyDataHallImpl());
		Naming.rebind(url + "ConfigurationDataService",new ConfigurationDataImpl());
		Naming.rebind(url + "DeliverDataService", new DeliverDataImpl());
		Naming.rebind(url + "ExamineManageService",examineManage);
		Naming.rebind(url + "ExamineSubmitService",new ExamineSubmitImpl(examineManage.getQueue()));
		Naming.rebind(url + "BankAccountDataService",new BankAccountDataImpl());
		Naming.rebind(url + "PaymentDataService",new PaymentDataImpl());
		Naming.rebind(url + "RevenueDataService",new RevenueDataImpl());
//		Naming.rebind(url + "InitialDataService",);
		Naming.rebind(url + "MemberDataService_driver",new DriverDataImpl());
		Naming.rebind(url + "MemberDataService_staff",new StaffDataImpl());
		Naming.rebind(url + "OrderDataService", new OrderDataImpl());
		Naming.rebind(url + "ReceiveDataService", new ReceiveDataImpl());
		Naming.rebind(url + "StoreFormDataService",new StoreFormDataImpl());
		Naming.rebind(url + "StoreModelDataService",new StoreModelDataImpl());
		Naming.rebind(url + "LogDataService",new LoadDataImpl());
		Naming.rebind(url + "CenterOutDataService",new CenterOutDataImpl());
		Naming.rebind(url + "LoadDataService", new LoadDataImpl());
	}
}
