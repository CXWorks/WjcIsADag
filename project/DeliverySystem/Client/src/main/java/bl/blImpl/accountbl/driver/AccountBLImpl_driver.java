package bl.blImpl.accountbl.driver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import message.OperationMessage;
import po.accountdata.AccountPO;

import rmi.accountdata.AccountDataService;


public class AccountBLImpl_driver {
	/**
	 * @param args
	 */
	public static void main(String [] args){
		
		try{
			AccountDataService AccountDataService=(AccountDataService)Naming.lookup("rmi://localhost:2333/AccountDataService");
			System.out.println(AccountDataService.getAccountPO(null) instanceof AccountPO);
			System.out.println(AccountDataService.getAccountPOs() instanceof ArrayList<?>);
			System.out.println(AccountDataService.insert(new AccountPO()) instanceof OperationMessage);
			System.out.println(AccountDataService.delete(null) instanceof OperationMessage);
			System.out.println(AccountDataService.update(new AccountPO()) instanceof OperationMessage);
			System.out.println(AccountDataService.checkAccount(null,null) instanceof OperationMessage);
			System.out.println(AccountDataService.newAccountID() instanceof String);
			
			
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
