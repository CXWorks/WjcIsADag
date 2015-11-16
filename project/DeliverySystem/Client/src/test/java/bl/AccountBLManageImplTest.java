package bl;

import bl.blImpl.accountbl.AccountBLManageImpl;
import bl.blService.accountblService.AccountBLManageService;
import bl.clientRMI.RMIHelper;
import mock.AccountDataMock;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import rmi.accountdata.AccountDataService;

/**
 * AccountBLManageImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ʮһ�� 16, 2015</pre>
 */
public class AccountBLManageImplTest {

    AccountBLManageService bl;
    AccountDataService data;

    @Before
    public void before() throws Exception {
        bl = new AccountBLManageImpl();
        data = new AccountDataMock();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getAccountVOs()
     */
    @Test
    public void testGetAccountVOs() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getAccountVO(String accountID)
     */
    @Test
    public void testGetAccountVO() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: addAccount(AccountVO vo)
     */
    @Test
    public void testAddAccount() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: deleteAccount(AccountVO vo)
     */
    @Test
    public void testDeleteAccount() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: modifyAccount(AccountVO vo)
     */
    @Test
    public void testModifyAccount() throws Exception {
//TODO: Test goes here... 
    }


} 
