package bl.blImpl.manangrbl;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import vo.managevo.car.CarVO;
import vo.managevo.institution.HallVO;
import bl.blService.manageblService.ManageblCarService;

/** 
 * Client//bl.blImpl.manangrbl//TestCarManage.java
 * @author CXWorks
 * @date 2015年11月16日 上午10:47:51
 * @version 1.0 
 */
public class TestCarManage {
	ManageblCarService car;
	HallVO h;
	CarVO c;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		car=new CarManage();
		h=new HallVO("1103");
		c=new CarVO(true, 0013, Calendar.getInstance(), null, 0, 0, 0, Calendar.getInstance());
		
	}

	@Test
	public void testManage() {
		assertTrue(car.addCar(c).operationResult);
		assertTrue(car.deleteCar(c).operationResult);
		assertTrue(car.modifyCar(c).operationResult);
	}
	@Test
	public void testGet(){
		assertNotNull(car.getCar(h));
		assertNotNull(car.newCarID());
		assertNotNull(car.searchCar(c));
	}

}
