package mock;

import message.OperationMessage;
import po.accountdata.AccountPO;
import po.accountdata.AuthorityEnum;
import rmi.accountdata.AccountDataService;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Sissel on 2015/11/16.
 */
public class AccountDataMock implements AccountDataService {

    private ArrayList<AccountPO> list;

    public AccountDataMock() {
        setupMock();
    }

    private void setupMock(){
        list = new ArrayList<>();
        list.add(new AccountPO("doraemon", AuthorityEnum.HAVE, "12345"));
        list.add(new AccountPO("cctv", AuthorityEnum.HAVE, "12345"));
        list.add(new AccountPO("naruto", AuthorityEnum.HAVE, "12345"));
        list.add(new AccountPO("luffy", AuthorityEnum.HAVE, "12345"));
        list.add(new AccountPO("goku", AuthorityEnum.HAVE, "12345"));
    }

    @Override
    public AccountPO getAccountPO(String accountID) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<AccountPO> getAccountPOs() throws RemoteException {
        return null;
    }

    @Override
    public OperationMessage insert(AccountPO po) throws RemoteException {
        return null;
    }

    @Override
    public OperationMessage delete(String accountID) throws RemoteException {
        return null;
    }

    @Override
    public OperationMessage update(AccountPO po) throws RemoteException {
        return null;
    }

    @Override
    public OperationMessage checkAccount(String id, String password) throws RemoteException {
        return null;
    }

    @Override
    public String newAccountID() {
        return null;
    }
}
