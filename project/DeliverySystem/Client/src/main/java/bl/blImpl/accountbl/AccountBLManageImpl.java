package bl.blImpl.accountbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bl.blService.accountblService.AccountBLManageService;
import bl.clientNetCache.CacheHelper;
import message.OperationMessage;
import po.accountdata.AccountPO;
import po.memberdata.StaffPO;
import rmi.accountdata.AccountDataService;
import rmi.memberdata.MemberDataService;
import tool.vopo.VOPOFactory;
import vo.accountvo.AccountVO;

/**
 * Client//bl.blImpl.accountbl//AccountBLManageImpl.java
 *
 * @author CXWorks
 * @date 2015年11月15日 下午3:56:31
 * @version 1.0
 */
public class AccountBLManageImpl implements AccountBLManageService {
	VOPOFactory vopoFactory;
	AccountDataService accountDataService;
	MemberDataService<StaffPO> staffDataService;

	public AccountBLManageImpl(VOPOFactory vopoFactory) {
		this.vopoFactory = vopoFactory;
		this.accountDataService = CacheHelper.getAccountDataService();
		this.staffDataService = CacheHelper.getMemberDataService_staff();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see bl.blService.accountblService.AccountBLManageService#getAccountVOs()
	 */
	public ArrayList<AccountVO> getAccountVOs() {
		try {
			ArrayList<AccountPO> po = accountDataService.getAccountPOs();
			ArrayList<AccountVO> vo = new ArrayList<AccountVO>(po.size());
			for (int i = 0; i < po.size(); i++) {
				AccountPO each = po.get(i);
				AccountVO temp = (AccountVO) vopoFactory.transPOtoVO(each);
				vo.add(temp);
			}
			return vo;
		} catch (RemoteException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * bl.blService.accountblService.AccountBLManageService#getAccountVO(java.
	 * lang.String)
	 */
	public AccountVO getAccountVO(String accountID) {
		try {
			AccountPO po = accountDataService.getAccountPO(accountID);
			AccountVO vo = (AccountVO) vopoFactory.transPOtoVO(po);
			return vo;
		} catch (RemoteException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see bl.blService.accountblService.AccountBLManageService#addAccount(po.
	 * accountdata.AccountPO)
	 */
	public OperationMessage addAccount(AccountVO vo) {
		try {
			if (staffDataService.getPerson(vo.getID()) != null || vo.getID().equals("admin")) {
				AccountPO po = (AccountPO) vopoFactory.transVOtoPO(vo);
				return accountDataService.insert(po);
			} else {
				return new OperationMessage(false, "系统中没有该员工信息");
			}
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * bl.blService.accountblService.AccountBLManageService#deleteAccount(po.
	 * accountdata.AccountPO)
	 */
	public OperationMessage deleteAccount(AccountVO vo) {
		String ID = vo.getID();
		try {
			return accountDataService.delete(ID);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * bl.blService.accountblService.AccountBLManageService#modifyAccount(po.
	 * accountdata.AccountPO)
	 */
	public OperationMessage modifyAccount(AccountVO vo) {
		AccountPO po = (AccountPO) vopoFactory.transVOtoPO(vo);
		try {
			return accountDataService.update(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLManageService#fuzzySearch(java.lang.String)
	 */
	@Override
	public List<AccountVO> fuzzySearch(String key) {
		try {
			ArrayList<AccountPO> pos=this.accountDataService.getAccountPOs();
			List<AccountVO> ans=((List<AccountVO>) vopoFactory.transPOtoVO(pos))
					.stream().filter(acc->{return acc.fuzzyCheck(key);})
					.collect(Collectors.toList());
			return ans;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}
