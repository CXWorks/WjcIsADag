package bl.blImpl.accountbl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bl.blService.accountblService.AccountBLLoginService;

/** 
 * Client//bl.blImpl.accountbl//TestAccountBLLoginImpl.java
 * @author CXWorks
 * @date 2015年11月16日 下午7:50:32
 * @version 1.0 
 */
public class TestAccountBLLoginImpl {
	AccountBLLoginService accountBLLoginService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		accountBLLoginService=new AccountBLLoginImpl();
	}

	/**
	 * Test method for {@link bl.blImpl.accountbl.AccountBLLoginImpl#checkAccount(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testCheckAccount() {
		assertTrue(accountBLLoginService.checkAccount("admin", "000000").operationResult);
		assertTrue(accountBLLoginService.checkAccount("141250018", "141250018").operationResult);
		assertTrue(accountBLLoginService.checkAccount("19303", "421212").operationResult);
	}

}
