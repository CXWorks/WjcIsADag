package bl.blImpl.configurationbl;

import java.util.ArrayList;

import message.OperationMessage;
import po.InfoEnum;
import rmi.configurationdata.ConfigurationDataService;
import vo.configurationvo.ConfigurationVO;
import bl.blService.configurationblService.ConfigurationBLService;
import bl.clientNetCache.CacheHelper;
import bl.tool.vopo.VOPOFactory;

/** 
 * Client//bl.blImpl.configurationbl//ConfigurationBLController.java
 * @author CXWorks
 * @date 2015年11月15日 下午4:40:00
 * @version 1.0 
 */
public class ConfigurationBLController implements ConfigurationBLService {
	private Distance distance;
	private Money money;
	private Salary salary;
	public ConfigurationBLController(VOPOFactory vopoFactory){
		ConfigurationDataService configurationDataService=CacheHelper.getConfigurationDataService();
		this.distance=new Distance(configurationDataService,vopoFactory);
		this.money=new Money(configurationDataService,vopoFactory);
		this.salary=new Salary(configurationDataService,vopoFactory);
	}

	/* (non-Javadoc)
	 * @see bl.blService.configurationblService.ConfigurationBLService#get(po.configurationdata.enums.ConfigurationEnum)
	 */
	public ArrayList<ConfigurationVO> get(InfoEnum type) {
		// TODO Auto-generated method stub
		return new ArrayList<ConfigurationVO>();
	}

	/* (non-Javadoc)
	 * @see bl.blService.configurationblService.ConfigurationBLService#modify(vo.configurationvo.ConfigurationVO)
	 */
	public OperationMessage modify(ConfigurationVO after) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

}
