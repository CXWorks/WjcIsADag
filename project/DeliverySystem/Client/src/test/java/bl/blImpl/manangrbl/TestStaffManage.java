package bl.blImpl.manangrbl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import po.memberdata.SexEnum;
import po.memberdata.StaffTypeEnum;
import vo.managevo.staff.StaffVO;
import bl.blService.manageblService.ManageblStaffService;
import tool.vopo.VOPOFactory;

/** 
 * @author Wjc
 * @date 2015年11月16日
 * @version 1.0 
 */
public class TestStaffManage {
	ManageblStaffService staff;
	StaffVO s;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		staff = new StaffManage(new VOPOFactory());
		s = new StaffVO(StaffTypeEnum.CENTER_COUNTERMAN,"111111","孟鑫",26,"111111",SexEnum.MAN,"无", null);
	}
	
	@Test
	public void testGetStaff(){
		assertNotNull(staff.getStaff(StaffTypeEnum.ADMINISTRATOR));
		assertNotNull(staff.getStaff(StaffTypeEnum.BURSAR));
		assertNotNull(staff.getStaff(StaffTypeEnum.CENTER_COUNTERMAN));
		assertNotNull(staff.getStaff(StaffTypeEnum.DELIVER));
		assertNotNull(staff.getStaff(StaffTypeEnum.HALL_COUNTERMAN));
		assertNotNull(staff.getStaff(StaffTypeEnum.MANAGER));
		assertNotNull(staff.getStaff(StaffTypeEnum.STOREMAN));
	}
	
	@Test
	public void testDeleteStaff(){
		assertTrue(staff.dismissStaff(s).operationResult);
	}
	
	@Test
	public void testModifyStaff(){
		assertTrue(staff.modifyStaff(s).operationResult);
	}
	
	@Test
	public void testSearchStaff(){
		assertNotNull(staff.searchStaff(s));
	}
	
	@Test
	public void testAddStaff(){
		assertTrue(staff.addStaff(s).operationResult);
	}
	
	@Test
	public void testNewStaffID(){
		assertNotNull(staff.newStaffID(StaffTypeEnum.BURSAR,""));
	}
	
}
