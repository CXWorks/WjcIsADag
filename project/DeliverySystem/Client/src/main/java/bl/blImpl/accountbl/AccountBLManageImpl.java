package bl.blImpl.accountbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.accountdata.AccountPO;
import rmi.accountdata.AccountDataService;
import vo.accountvo.AccountVO;
import bl.blService.accountblService.AccountBLManageService;
import bl.clientNetCache.CacheHelper;
import bl.tool.vopo.VOPOFactory;

/** 
 * Client//bl.blImpl.accountbl//AccountBLManageImpl.java
 * @author CXWorks
 * @date 2015年11月15日 下午3:56:31
 * @version 1.0 
 */
public class AccountBLManageImpl implements AccountBLManageService{
	VOPOFactory vopoFactory;
	AccountDataService accountDataService;
	public AccountBLManageImpl(VOPOFactory vopoFactory){
		this.vopoFactory=vopoFactory;
		this.accountDataService=CacheHelper.getAccountDataService();
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLManageService#getAccountVOs()
	 */
	public ArrayList<AccountVO> getAccountVOs() {
		try {
			ArrayList<AccountPO> po=accountDataService.getAccountPOs();
			ArrayList<AccountVO> vo=new ArrayList<AccountVO>(po.size());
			for (int i = 0; i < po.size(); i++) {
				AccountVO temp=new AccountVO(po.get(i));
				vo.add(temp);
			}
			return vo;
		} catch (RemoteException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLManageService#getAccountVO(java.lang.String)
	 */
	public AccountVO getAccountVO(String accountID) {
		try {
			AccountPO po=accountDataService.getAccountPO(accountID);
			AccountVO vo=new AccountVO(po);
			return vo;
		} catch (RemoteException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLManageService#addAccount(po.accountdata.AccountPO)
	 */
	public OperationMessage addAccount(AccountVO vo) {
		AccountPO po=(AccountPO)vopoFactory.transVOtoPO(vo);
		try {
			return accountDataService.insert(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLManageService#deleteAccount(po.accountdata.AccountPO)
	 */
	public OperationMessage deleteAccount(AccountVO vo) {
		String ID=vo.getID();
		try {
			return accountDataService.delete(ID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLManageService#modifyAccount(po.accountdata.AccountPO)
	 */
	public OperationMessage modifyAccount(AccountVO vo) {
		AccountPO po=(AccountPO)vopoFactory.transVOtoPO(vo);
		try {
			return accountDataService.update(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}

}
