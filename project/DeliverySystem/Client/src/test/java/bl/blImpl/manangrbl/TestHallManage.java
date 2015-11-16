package bl.blImpl.manangrbl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import vo.managevo.institution.HallVO;
import bl.blService.manageblService.ManageblHallService;

/** 
 * @author Wjc
 * @date 2015年11月16日
 * @version 1.0 
 */
public class TestHallManage {
	ManageblHallService hall;
	HallVO h;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		hall = new HallManage();
		h = new HallVO();
	}
	
	@Test
	public void testGetHall(){
		assertNotNull(hall.getHall());
	}
	
	@Test
	public void testDeleteHall(){
		assertTrue(hall.deleteHall(h).operationResult);
	}
	
	@Test
	public void testModifyHall(){
		assertTrue(hall.modifyHall(h).operationResult);
	}
	
	@Test
	public void testSearchHall(){
		assertNotNull(hall.searchHall(h));
	}
	
	@Test
	public void testNewHallID(){
		assertNotNull(hall.newHallID("111111"));
	}
	
	@Test
	public void testAddHall(){
		assertTrue(hall.addHall(h).operationResult);
	}
	
	@Test
	public void testNewInstitutionDistance(){
		assertTrue(hall.newInstitutionDistance("111111",new Object()).operationResult);
	}
}
