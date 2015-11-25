package bl.blImpl.accountbl;

import java.rmi.RemoteException;

import po.memberdata.StaffPO;
import rmi.accountdata.AccountDataService;
import rmi.memberdata.MemberDataService;
import userinfo.UserInfo;
import message.OperationMessage;
import bl.blService.accountblService.AccountBLLoginService;
import bl.clientNetCache.CacheHelper;

/** 
 * Client//bl.blImpl.accountbl//AccountBLLoginServiceImpl.java
 * @author CXWorks
 * @date 2015年11月15日 下午3:54:17
 * @version 1.0 
 */
public class AccountBLLoginImpl implements AccountBLLoginService {
	AccountDataService accountDataService;
	public AccountBLLoginImpl(){
		this.accountDataService=CacheHelper.getAccountDataService();
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLLoginService#checkAccountat(java.lang.String, java.lang.String)
	 */
	public OperationMessage checkAccount(String id, String password) {
		try {
			OperationMessage check=accountDataService.checkAccount(id, password);
			if (check.operationResult) {
//				MemberDataService< StaffPO> memberDataService=CacheHelper.getMemberDataService();
//				StaffPO user=memberDataService.getPerson(id);
//				UserInfo.setInfo(user.getID(), user.getStaff(), user.getInititutionID());
				return new OperationMessage();
			} else {
				return check;
			}
			
		} catch (RemoteException e) {
			return new OperationMessage(false,"net error");
		}
	}

}
