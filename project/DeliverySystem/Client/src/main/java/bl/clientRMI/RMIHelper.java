package bl.clientRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.jar.Attributes.Name;

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

/**
 * Client//bl.clientRMI//RMIHelper.java
 * @author CXWorks
 * @date 2015年10月30日 下午5:13:14
 * @version 1.0
 */
public class RMIHelper {
	private static final String IP="172.25.169.30:2333";

	private static boolean initialized=false;

	private static OrderDataService orderDataService;
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
	private static InitialDataService initialDataService;
	private static MemberDataService<DriverPO> memberDataService_driver;
	private static MemberDataService<StaffPO> memberDataService_staff;
	private static ReceiveDataService receiveDataService;
	private static StoreFormDataService storeFormDataService;
	private static StoreModelDataService storeModelDataService;
	private static LogDataService logDataService;
	private static CenterOutDataService transportDataService;
	private static LoadDataService loadDataService;




	public synchronized static void	init() throws NetInitException {
		if(initialized)
			return;
		try {
			initDataService();
		} catch (Exception e) {
			throw new NetInitException(e);
		}
	}
	//
	private static void initDataService() throws MalformedURLException, RemoteException, NotBoundException{
		String url="rmi://"+IP+"/";
		orderDataService=(OrderDataService)Naming.lookup(url+"OrderDataService");
		accountDataService=(AccountDataService)Naming.lookup(url+"AccountDataService");
		chatRemindService=(ChatRemindService)Naming.lookup(url+"ChatRemindService");
		companyDataCarService=(CompanyDataCarService)Naming.lookup(url+"CompanyDataCarService");
		companyDataCenterService=(CompanyDataCenterService)Naming.lookup(url+"CompanyDataCenterService");
		companyDataHallService=(CompanyDataHallService)Naming.lookup(url+"CompanyDataHallService");
		configurationDataService=(ConfigurationDataService)Naming.lookup(url+"ConfigurationDataService");
		deliverDataService = (DeliverDataService)Naming.lookup(url+"DeliverDataService");
		examineManageService=(ExamineManageService)Naming.lookup(url+"ExamineManageService");
		examineSubmitService=(ExamineSubmitService)Naming.lookup(url+"ExamineSubmitService");
		bankAccountDataService=(BankAccountDataService)Naming.lookup(url+"BankAccountDataService");
		paymentDataService=(PaymentDataService)Naming.lookup(url+"PaymentDataService");
		revenueDataService=(RevenueDataService)Naming.lookup(url+"RevenueDataService");
		memberDataService_driver=(MemberDataService<DriverPO>)Naming.lookup(url+"MemberDataService_driver");
		memberDataService_staff=(MemberDataService<StaffPO>)Naming.lookup(url+"MemberDataService_staff");
		receiveDataService=(ReceiveDataService)Naming.lookup(url+"ReceiveDataService");
		loadDataService=(LoadDataService)Naming.lookup(url+"LoadDataService");
		storeFormDataService=(StoreFormDataService)Naming.lookup(url+"StoreFormDataService");
		storeModelDataService=(StoreModelDataService)Naming.lookup(url+"StoreModelDataService");
		logDataService=(LogDataService)Naming.lookup(url+"LogDataService");
		transportDataService=(CenterOutDataService)Naming.lookup(url+"CenterOutDataService");
		loadDataService=(LoadDataService)Naming.lookup(url+"LoadDataService");


	}
	public static LogDataService getLogDataService(){
		return logDataService;
	}

	public static LoadDataService getLoadDataService() {
		return loadDataService;
	}
	//
	public static OrderDataService getOrderDataService(){
		return orderDataService;
	}
	public static boolean isInitialized() {
		return initialized;
	}
	public static AccountDataService getAccountDataService() {
		return accountDataService;
	}
	public static ChatRemindService getChatRemindService() {
		return chatRemindService;
	}
	public static CompanyDataCarService getCompanyDataCarService() {
		return companyDataCarService;
	}
	public static CompanyDataCenterService getCompanyDataCenterService() {
		return companyDataCenterService;
	}
	public static CompanyDataHallService getCompanyDataHallService() {
		return companyDataHallService;
	}
	public static ConfigurationDataService getConfigurationDataService() {
		return configurationDataService;
	}
	public static DeliverDataService getDeliverDataService() {
		return deliverDataService;
	}
	public static ExamineManageService getExamineManageService() {
		return examineManageService;
	}
	public static ExamineSubmitService getExamineSubmitService() {
		return examineSubmitService;
	}
	public static BankAccountDataService getBankAccountDataService() {
		return bankAccountDataService;
	}

	public static PaymentDataService getPaymentDataService() {
		return paymentDataService;
	}
	public static RevenueDataService getRevenueDataService() {
		return revenueDataService;
	}
	public static InitialDataService getInitialDataService() {
		return initialDataService;
	}
	public static ReceiveDataService getReceiveDataService() {
		return receiveDataService;
	}
	public static StoreFormDataService getStoreFormDataService() {
		return storeFormDataService;
	}
	public static StoreModelDataService getStoreModelDataService() {
		return storeModelDataService;
	}

	public static CenterOutDataService getTransportDataService() {
		return transportDataService;
	}
	public static MemberDataService<DriverPO> getMemberDataService_driver() {
		return memberDataService_driver;
	}
	public static MemberDataService<StaffPO> getMemberDataService_staff() {
		return memberDataService_staff;
	}

}
