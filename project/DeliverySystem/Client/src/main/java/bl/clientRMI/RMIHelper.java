package bl.clientRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.jar.Attributes.Name;

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
import rmi.transportdata.LoadDataService;

/** 
 * Client//bl.clientRMI//RMIHelper.java
 * @author CXWorks
 * @date 2015年10月30日 下午5:13:14
 * @version 1.0 
 */
public class RMIHelper {
	private static final String IP="localhost:2333";
	
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
	private static MemberDataService memberDataService;
	private static ReceiveDataService receiveDataService;
	private static StoreFormDataService storeFormDataService;
	private static StoreModelDataService storeModelDataService;
	private static SystemDataService systemDataService;
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
//		orderDataService=(OrderDataService)Naming.lookup("rmi://localhost:2333/OrderDataService");
//		receiveDataService=(ReceiveDataService)Naming.lookup("rmi://localhost:2333/ReceiveDataService");
//		loadDataService=(LoadDataService)Naming.lookup("rmi://localhost:2333/LoadDataService");
		accountDataService = (AccountDataService)Naming.lookup("rmi://localhost:2333/AccountDataService");
		deliverDataService = (DeliverDataService)Naming.lookup("rmi://localhost:2333/DeliverDataService");
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
	public static MemberDataService getMemberDataService() {
		return memberDataService;
	}
	public static ReceiveDataService getReceiveDataService() {
		System.out.println("fuck");
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
