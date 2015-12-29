package bl.blImpl.accountbl;

import java.rmi.RemoteException;

import po.memberdata.StaffPO;
import rmi.accountdata.AccountDataService;
import rmi.memberdata.MemberDataService;
import rmi.systemdata.SystemDataService;
import userinfo.UserInfo;
import message.OperationMessage;
import bl.NetReconnect.Reconnect;
import bl.blService.accountblService.AccountBLLoginService;
import bl.clientNetCache.CacheHelper;
import bl.clientRMI.NetInitException;
import bl.clientRMI.RMIHelper;

/**
 * Client//bl.blImpl.accountbl//AccountBLLoginServiceImpl.java
 * @author CXWorks
 * @date 2015年11月15日 下午3:54:17
 * @version 1.0
 */
public class AccountBLLoginImpl implements AccountBLLoginService {
	public AccountBLLoginImpl(){
	}
	
	private void initNet(){
		try {
			CacheHelper.initializeLogin();
		} catch (NetInitException e) {
			Reconnect.ReConnectFactory();
		}
		
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLLoginService#checkAccountat(java.lang.String, java.lang.String)
	 */
	public OperationMessage checkAccount(String id, String password) {
		try {
			this.initNet();
			AccountDataService accountDataService=CacheHelper.getAccountDataService();
			OperationMessage check=accountDataService.checkAccount(id, password);
			if (check.operationResult) {
				CacheHelper.initCacheService();
				MemberDataService< StaffPO> memberDataService=CacheHelper.getMemberDataService_staff();
				StaffPO user=memberDataService.getPerson(id);
				//
				
				UserInfo.setInfo(user.getID(), user.getStaff(), user.getInititutionID(),user.getName());
				UserInfo.changeSystermState();
				//
				accountDataService.setAccount(id, true);
				return new OperationMessage();
			} else {
				return check;
			}

		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return new OperationMessage(false,"net error");
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLLoginService#logOut()
	 */
	@Override
	public OperationMessage logOut() {
		try {
			AccountDataService accountDataService=CacheHelper.getAccountDataService();
			return accountDataService.setAccount(UserInfo.getUserID(), false);
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return new OperationMessage(false, "net error");
		}
	}

}
