package bl.blImpl.storebl;

import java.rmi.RemoteException;

import bl.NetReconnect.Reconnect;
import bl.clientNetCache.CacheHelper;
import rmi.configurationdata.ConfigurationDataService;
import userinfo.UserInfo;
import message.OperationMessage;

/** 
 * Client//bl.blImpl.storebl//StoreWarningChecker.java
 * @author CXWorks
 * @date 2015年12月8日 上午12:38:32
 * @version 1.0 
 */
public class StoreWarningChecker {
	private static double warningLine;
	public StoreWarningChecker(){
		ConfigurationDataService configurationDataService=CacheHelper.getConfigurationDataService();
		try {
			this.warningLine=configurationDataService.getWarningline(UserInfo.getInstitutionID());
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
		}
	}
	//
	public OperationMessage checkOperation(){
		
		return new OperationMessage();
	}
	public static double getWarningLine(){
		return warningLine;
	}
	public OperationMessage setWarningLine(double warningLine){
		ConfigurationDataService configurationDataService=CacheHelper.getConfigurationDataService();
		try {
			OperationMessage res= configurationDataService.setWarningline(UserInfo.getInstitutionID(), warningLine);
			if (res.operationResult) {
				this.warningLine=warningLine;
				return res;
			}
			return new OperationMessage(false, "unknown error");
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return new OperationMessage(false, e.getMessage());
		}
	}
}
