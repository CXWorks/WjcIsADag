package bl.clientNetCache;

import java.rmi.RemoteException;

import bl.clientNetCache.dataProxy.ConfigurationDataProxy;
import bl.clientRMI.NetInitException;
import bl.clientRMI.RMIHelper;
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
import rmi.storedata.TackDataService;
import rmi.systemdata.LogDataService;
import rmi.systemdata.SystemDataService;
import rmi.transportdata.CenterOutDataService;
import rmi.transportdata.LoadDataService;

/**
 * Client//bl.clientNetCache//CacheHelper.java
 * @author CXWorks
 * @date 2015年10月30日 下午7:16:58
 * @version 1.0
 */
public class CacheHelper {
	//
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
	private static CenterOutDataService transportDataService;
	private static LoadDataService loadDataService;

	private static LogDataService logDataService;
	private static SystemDataService systemDataService;
	private static TackDataService tackDataService;

	public static void  initializeLogin() throws NetInitException{
		RMIHelper.init();
		accountDataService=RMIHelper.getAccountDataService();
		orderDataService=RMIHelper.getOrderDataService();
		deliverDataService=RMIHelper.getDeliverDataService();
		revenueDataService=RMIHelper.getRevenueDataService();
		receiveDataService=RMIHelper.getReceiveDataService();
		storeFormDataService=RMIHelper.getStoreFormDataService();
		transportDataService=RMIHelper.getTransportDataService();
		loadDataService=RMIHelper.getLoadDataService();
		memberDataService_driver=RMIHelper.getMemberDataService_driver();
		memberDataService_staff=RMIHelper.getMemberDataService_staff();
	}

	//
	public static void initCacheService(){

		chatRemindService=RMIHelper.getChatRemindService();
		companyDataCarService=RMIHelper.getCompanyDataCarService();
		companyDataCenterService=RMIHelper.getCompanyDataCenterService();
		companyDataHallService=RMIHelper.getCompanyDataHallService();
		if (true) {
			try {
				configurationDataService=ConfigurationDataProxy.getInstance();
			} catch (RemoteException e) {
				configurationDataService=RMIHelper.getConfigurationDataService();
			}
		}else {
			configurationDataService=RMIHelper.getConfigurationDataService();
		}

		//

		examineManageService=RMIHelper.getExamineManageService();
		examineSubmitService=RMIHelper.getExamineSubmitService();
		bankAccountDataService=RMIHelper.getBankAccountDataService();
		paymentDataService=RMIHelper.getPaymentDataService();

		initialDataService=RMIHelper.getInitialDataService();


		storeModelDataService=RMIHelper.getStoreModelDataService();
		logDataService=RMIHelper.getLogDataService();

		initialDataService=RMIHelper.getInitialDataService();
		systemDataService=RMIHelper.getSystemDataService();
		tackDataService=RMIHelper.getTackDataService();
	}
	//
	public static void reconnect() throws NetInitException{
		CacheHelper.initializeLogin();
		CacheHelper.initCacheService();
	}
	//
	
	public static SystemDataService getSystemDataService(){
		return systemDataService;
	}
	public static TackDataService getTackDataService() {
		return tackDataService;
	}

	public static OrderDataService getOrderDataService() {
		return orderDataService;
	}
	public static InitialDataService getInitialDataService() {
		return initialDataService;
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
//	public static InitialDataService getInitialDataService() {
//		return initialDataService;
//	}
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
	public static LoadDataService getLoadDataService() {
		return loadDataService;
	}
	public static MemberDataService<DriverPO> getMemberDataService_driver() {
		return memberDataService_driver;
	}
	public static MemberDataService<StaffPO> getMemberDataService_staff() {
		return memberDataService_staff;
	}
	public static LogDataService getLogDataService(){
		return logDataService;
	}

}
