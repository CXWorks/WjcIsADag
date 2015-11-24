package bl.blImpl.configurationbl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import po.configurationdata.PackPO;
import po.configurationdata.PricePO;
import vo.configurationvo.PackVO;
import vo.configurationvo.PriceVO;
import bl.blService.configurationblService.ConfigurationBLService;

/** 
 * Client//bl.blImpl.configurationbl//TestConfigurationBLController.java
 * @author CXWorks
 * @date 2015年11月16日 下午7:51:46
 * @version 1.0 
 */
public class TestConfigurationBLController {
	ConfigurationBLService configurationBLService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		configurationBLService = new ConfigurationBLController();
	}

	/**
	 * Test method for {@link bl.blImpl.configurationbl.ConfigurationBLController#get(po.configurationdata.enums.ConfigurationEnum)}.
	 */
	@Test
	public void testGet() {
//		assertNotNull(configurationBLService.get(ConfigurationEnum.CITY_DISTANCE));
//		assertNotNull(configurationBLService.get(ConfigurationEnum.PACK));
//		assertNotNull(configurationBLService.get(ConfigurationEnum.PRICE));
//		assertNotNull(configurationBLService.get(ConfigurationEnum.PRICE_PROPORTION));
//		assertNotNull(configurationBLService.get(ConfigurationEnum.SALARY_STRATEGY));
	}

	/**
	 * Test method for {@link bl.blImpl.configurationbl.ConfigurationBLController#modify(vo.configurationvo.ConfigurationVO)}.
	 */
	@Test
	public void testModify() {
		assertTrue(configurationBLService.modify(new PackVO()).operationResult);
		assertTrue(configurationBLService.modify(new PriceVO()).operationResult);
		
		
	}

}
