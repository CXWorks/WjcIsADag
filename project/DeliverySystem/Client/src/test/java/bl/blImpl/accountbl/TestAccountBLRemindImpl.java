package bl.blImpl.accountbl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bl.blService.accountblService.AccountBLRemindService;

/** 
 * Client//bl.blImpl.accountbl//TestAccountBLRemindImpl.java
 * @author CXWorks
 * @date 2015年11月16日 下午7:51:22
 * @version 1.0 
 */
public class TestAccountBLRemindImpl {
	AccountBLRemindService accountBLRemindService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		accountBLRemindService=new AccountBLRemindImpl();
	}

	/**
	 * Test method for {@link bl.blImpl.accountbl.AccountBLRemindImpl#checkMessage(java.lang.String)}.
	 */
	@Test
	public void testCheckMessage() {
		assertTrue(accountBLRemindService.checkMessage("admin").operationResult);
		assertTrue(accountBLRemindService.checkMessage("141250018").operationResult);
		assertTrue(accountBLRemindService.checkMessage("432432").operationResult);
	}

	/**
	 * Test method for {@link bl.blImpl.accountbl.AccountBLRemindImpl#receive(java.lang.String)}.
	 */
	@Test
	public void testReceive() {
		assertNotNull(accountBLRemindService.receive("admin"));
		assertNotNull(accountBLRemindService.receive("141250018"));
		assertNotNull(accountBLRemindService.receive("535323"));
	}

}
