package bl.blImpl.accountbl;

import java.rmi.RemoteException;

import po.memberdata.StaffPO;
import rmi.accountdata.AccountDataService;
import rmi.memberdata.MemberDataService;
import rmi.systemdata.SystemDataService;
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
				MemberDataService< StaffPO> memberDataService=CacheHelper.getMemberDataService_staff();
				StaffPO user=memberDataService.getPerson(id);
				//
				SystemDataService systemDataService=CacheHelper.getSystemDataService();
				UserInfo.setInfo(user.getID(), user.getStaff(), user.getInititutionID(),user.getName(),systemDataService.checkSystemState());
				System.out.println(UserInfo.getStaffType());
				return new OperationMessage();
			} else {
				return check;
			}

		} catch (RemoteException e) {
			return new OperationMessage(false,"net error");
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLLoginService#logOut()
	 */
	@Override
	public OperationMessage logOut() {
		try {
			return this.accountDataService.setAccount(UserInfo.getUserID(), false);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}

}
