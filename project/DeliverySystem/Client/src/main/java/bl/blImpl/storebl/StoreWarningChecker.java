package bl.blImpl.storebl;

import java.rmi.RemoteException;

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
	private double warningLine;
	private ConfigurationDataService configurationDataService;
	public StoreWarningChecker(){
		configurationDataService=CacheHelper.getConfigurationDataService();
		try {
			this.warningLine=configurationDataService.getWarningline(UserInfo.getInstitutionID());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	//
	public OperationMessage checkOperation(){
		
		return new OperationMessage();
	}
	public double getWarningLine(){
		return warningLine;
	}
	public OperationMessage setWarningLine(double warningLine){
		try {
			OperationMessage res= configurationDataService.setWarningline(UserInfo.getInstitutionID(), warningLine);
			if (res.operationResult) {
				this.warningLine=warningLine;
				return res;
			}
			return new OperationMessage(false, "unknown error");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new OperationMessage(false, e.getMessage());
		}
	}
}
