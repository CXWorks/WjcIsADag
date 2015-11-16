package bl.blImpl.financebl;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import vo.financevo.BankAccountVO;
import bl.blService.financeblService.BankAccountBLService;

/** 
 * Client//bl.blImpl.financebl//TestBankAccountBL.java
 * @author CXWorks
 * @date 2015年11月16日 上午9:49:10
 * @version 1.0 
 */
public class TestBankAccountBL {
	BankAccountBLService bankAccountBLService;
	List<BankAccountVO> list;
	BankAccountVO b;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		bankAccountBLService=new BankAccountBLImpl();
		list=new LinkedList<BankAccountVO>();
		b=new BankAccountVO("42084", "2333", "1000000");
		list.add(b);
		BankAccountVO[] t=new BankAccountVO[3];
		t[0]=new BankAccountVO("42084", "2333", "1000000");
		t[1]=new BankAccountVO("42584", "2333", "10");
		t[2]=new BankAccountVO("43284", "2333", "-100");
	}

	@Test
	public void testGet() {
		assertNotNull(bankAccountBLService.getAllAccounts());
		assertNotNull(bankAccountBLService.getTradeHistory(b));
		assertNotNull(bankAccountBLService.filterAccounts(list, "3311"));
		
	}
	@Test
	public void testManage(){
		assertTrue(bankAccountBLService.addAccount(b).operationResult);
		assertTrue(bankAccountBLService.deleteAccount(b).operationResult);
		assertTrue(bankAccountBLService.editAccount(b, "fssa").operationResult);
		assertTrue(bankAccountBLService.pay("3212", "200").operationResult);
		assertTrue(bankAccountBLService.receive("43223", "10000").operationResult);
	}

}
