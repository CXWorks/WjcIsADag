package bl.blImpl.accountbl;

import java.util.ArrayList;

import message.OperationMessage;
import po.accountdata.AccountPO;
import vo.accountvo.AccountVO;
import bl.blService.accountblService.AccountBLService;

public class AccountBLImpl implements AccountBLService{

	public ArrayList<AccountVO> getAccountVOs() {
		// TODO Auto-generated method stub
		ArrayList<AccountVO> result=new ArrayList<AccountVO>();
		AccountVO stub=new AccountVO();
		result.add(stub);
		return result;
	}

	public AccountVO getAccountVO(String accountID) {
		// TODO Auto-generated method stub
		return new AccountVO();
	}

	public OperationMessage addAccount(AccountPO po) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage deleteAccount(AccountPO po) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage modifyAccount(AccountPO po) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage checkAccountat(String id, String password) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

}
