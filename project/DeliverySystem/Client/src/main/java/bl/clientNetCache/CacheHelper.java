package bl.clientNetCache;

import bl.clientRMI.NetInitException;
import bl.clientRMI.RMIHelper;
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
import rmi.systemdata.SystemDataService;
import rmi.transportdata.CenterOutDataService;

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
	private static MemberDataService memberDataService;
	private static ReceiveDataService receiveDataService;
	private static StoreFormDataService storeFormDataService;
	private static StoreModelDataService storeModelDataService;
	private static SystemDataService systemDataService;
	private static CenterOutDataService transportDataService;
	//
	public static void  initializeCache() throws NetInitException{
		RMIHelper.init();
		if(initStoredData()){
			updateCache();
		}
		initCacheService();
		
	}
	//
	private static boolean initStoredData(){
		return true;
	}
	//
	private static void updateCache(){
		
	}
	//
	private static void initCacheService(){
		orderDataService=RMIHelper.getOrderDataService();
		receiveDataService=RMIHelper.getReceiveDataService();
	}
	//
	public static OrderDataService getOrderDataService() {
		return orderDataService;
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
	public static MemberDataService getMemberDataService() {
		return memberDataService;
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
	public static SystemDataService getSystemDataService() {
		return systemDataService;
	}
	public static CenterOutDataService getTransportDataService() {
		return transportDataService;
	}
	
	
}
