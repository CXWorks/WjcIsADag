package bl.blImpl.manangrbl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tool.vopo.VOPOFactory;
import vo.managevo.institution.CenterVO;
import bl.blService.manageblService.ManageblCenterService;

/** 
 * @author Wjc
 * @date 2015年11月16日
 * @version 1.0 
 */
public class TestCenterManage {
	ManageblCenterService center;
	CenterVO c;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		center = new CenterManage(new VOPOFactory());
		c = new CenterVO("111111","上海",null,null);
	}
	
	@Test
	public void testGetCenter(){
		assertNotNull(center.getCenter());
	}
	
	@Test
	public void testDeleteCenter(){
		assertTrue(center.deleteCenter(c).operationResult);
	}
	
	@Test
	public void testModifyCenter(){
		assertTrue(center.modifyCenter(c).operationResult);
	}
	
	@Test
	public void testSearchCenter(){
		assertNotNull(center.searchCenter(c));
	}
	
	@Test
	public void testAddCenter(){
		assertTrue(center.addCenter(c).operationResult);
	}
	
	@Test
	public void testNewInstitutionDistance(){
		assertTrue(center.newInstitutionDistance("111111",new Object()).operationResult);
	}
}
