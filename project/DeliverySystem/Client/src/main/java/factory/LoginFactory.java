package factory;

import bl.blImpl.accountbl.AccountBLLoginImpl;
import bl.blService.accountblService.AccountBLLoginService;;

/** 
 * Client//factory//LoginFactory.java
 * @author CXWorks
 * @date 2015年11月21日 下午11:20:07
 * @version 1.0 
 */
public class LoginFactory extends BLFactory{
	private AccountBLLoginService accountBLLoginService;
	public LoginFactory(){
		accountBLLoginService=new AccountBLLoginImpl();
	}
	public AccountBLLoginService getAccountBLLoginService() {
		return accountBLLoginService;
	}
	
	
}
