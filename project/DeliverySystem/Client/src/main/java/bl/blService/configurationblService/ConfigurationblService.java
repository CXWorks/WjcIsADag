package bl.blService.configurationblService;

import java.util.ArrayList;

import message.OperationMessage;
import po.configurationdata.enums.ConfigurationEnum;
import vo.configurationvo.ConfigurationVO;

/** 
 * Client//bl.blService.configurationblService//ConfigurationBLServicet.java
 * @author CXWorks
 * @date 2015年11月15日 下午4:21:53
 * @version 1.0 
 */
public interface ConfigurationBLService {
	/**
	 * 获取信息
	 * @param type
	 * @return
	 */
	public ArrayList<ConfigurationVO> get(ConfigurationEnum type);
	/**
	 * 修改信息
	 * @param after
	 * @return
	 */
	public OperationMessage modify(ConfigurationVO after);
}
