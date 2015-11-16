package bl.blImpl.accountbl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import po.accountdata.AuthorityEnum;
import vo.accountvo.AccountVO;
import bl.blService.accountblService.AccountBLManageService;

/** 
 * Client//bl.blImpl.accountbl//TestAccountBLManageImpl.java
 * @author CXWorks
 * @date 2015年11月16日 下午7:51:02
 * @version 1.0 
 */
public class TestAccountBLManageImpl {
	AccountBLManageService accountBLManageService;
	AccountVO[] vo;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		accountBLManageService=new AccountBLManageImpl();
		vo=new AccountVO[3];
		vo[0]=new AccountVO("141250018", "141250018", AuthorityEnum.DONT_HAVE);
		vo[1]=new AccountVO("admin", "141250018", AuthorityEnum.HAVE);
		vo[2]=new AccountVO("432524", "rtre", AuthorityEnum.DONT_HAVE);
	}

	/**
	 * Test method for {@link bl.blImpl.accountbl.AccountBLManageImpl#getAccountVOs()}.
	 */
	@Test
	public void testGetAccountVOs() {
		assertNotNull(accountBLManageService.getAccountVOs());
	}

	/**
	 * Test method for {@link bl.blImpl.accountbl.AccountBLManageImpl#getAccountVO(java.lang.String)}.
	 */
	@Test
	public void testGetAccountVO() {
		assertNotNull(accountBLManageService.getAccountVO("admin"));
		assertNotNull(accountBLManageService.getAccountVO("14125008"));
	}

	/**
	 * Test method for {@link bl.blImpl.accountbl.AccountBLManageImpl#addAccount(vo.accountvo.AccountVO)}.
	 */
	@Test
	public void testAddAccount() {
		for (int i = 0; i < vo.length; i++) {
			assertTrue(accountBLManageService.addAccount(vo[i]).operationResult);
		}
	}

	/**
	 * Test method for {@link bl.blImpl.accountbl.AccountBLManageImpl#deleteAccount(vo.accountvo.AccountVO)}.
	 */
	@Test
	public void testDeleteAccount() {
		for (int i = 0; i < vo.length; i++) {
			assertTrue(accountBLManageService.deleteAccount(vo[i]).operationResult);
		}
	}

	/**
	 * Test method for {@link bl.blImpl.accountbl.AccountBLManageImpl#modifyAccount(vo.accountvo.AccountVO)}.
	 */
	@Test
	public void testModifyAccount() {
		for (int i = 0; i < vo.length; i++) {
			assertTrue(accountBLManageService.modifyAccount(vo[i]).operationResult);
		}
	}

}
