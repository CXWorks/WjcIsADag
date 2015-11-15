package bl.blImpl.accountbl;

import java.util.ArrayList;

import message.OperationMessage;
import po.accountdata.AccountPO;
import vo.accountvo.AccountVO;
import bl.blService.accountblService.AccountBLManageService;
import bl.clientNetCache.CacheHelper;

/** 
 * Client//bl.blImpl.accountbl//AccountBLManageImpl.java
 * @author CXWorks
 * @date 2015年11月15日 下午3:56:31
 * @version 1.0 
 */
public class AccountBLManageImpl implements AccountBLManageService{

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLManageService#getAccountVOs()
	 */
	public ArrayList<AccountVO> getAccountVOs() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLManageService#getAccountVO(java.lang.String)
	 */
	public AccountVO getAccountVO(String accountID) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLManageService#addAccount(po.accountdata.AccountPO)
	 */
	public OperationMessage addAccount(AccountVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLManageService#deleteAccount(po.accountdata.AccountPO)
	 */
	public OperationMessage deleteAccount(AccountVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLManageService#modifyAccount(po.accountdata.AccountPO)
	 */
	public OperationMessage modifyAccount(AccountVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
