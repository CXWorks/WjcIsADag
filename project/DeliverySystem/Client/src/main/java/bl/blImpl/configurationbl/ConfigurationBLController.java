package bl.blImpl.configurationbl;

import java.util.ArrayList;

import message.OperationMessage;
import po.configurationdata.enums.ConfigurationEnum;
import vo.configurationvo.ConfigurationVO;
import bl.blService.configurationblService.ConfigurationBLService;

/** 
 * Client//bl.blImpl.configurationbl//ConfigurationBLController.java
 * @author CXWorks
 * @date 2015年11月15日 下午4:40:00
 * @version 1.0 
 */
public class ConfigurationBLController implements ConfigurationBLService {
	Distance distance;
	Money money;
	Salary salary;

	/* (non-Javadoc)
	 * @see bl.blService.configurationblService.ConfigurationBLService#get(po.configurationdata.enums.ConfigurationEnum)
	 */
	public ArrayList<ConfigurationVO> get(ConfigurationEnum type) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.configurationblService.ConfigurationBLService#modify(vo.configurationvo.ConfigurationVO)
	 */
	public OperationMessage modify(ConfigurationVO after) {
		// TODO Auto-generated method stub
		return null;
	}

}
