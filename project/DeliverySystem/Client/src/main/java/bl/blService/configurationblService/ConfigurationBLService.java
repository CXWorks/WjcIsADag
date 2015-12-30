package bl.blService.configurationblService;

import java.util.ArrayList;
import java.util.List;

import po.InfoEnum;
import message.OperationMessage;
import vo.configurationvo.City2DVO;
import vo.configurationvo.ConfigurationVO;
import vo.configurationvo.PackVO;
import vo.configurationvo.PriceVO;
import vo.configurationvo.ProportionVO;
import vo.configurationvo.SalaryStrategyVO;

/** 
 * Client//bl.blService.configurationblService//ConfigurationBLServicet.java
 * @author CXWorks
 * @date 2015年11月15日 下午4:21:53
 * @version 1.0 
 */
public interface ConfigurationBLService {
	public List<City2DVO> getCity();
	public OperationMessage modifyCity(City2DVO city2dvo);
	public OperationMessage newCity(City2DVO city2dvo);
	public OperationMessage deleteCity(City2DVO city2dvo);
	//
	/**
	 * 获取信息
	 * @param type
	 * @return
	 */
	public ArrayList<ConfigurationVO> get(InfoEnum type);
	/**
	 * 修改信息
	 * @param after
	 * @return
	 */
	public OperationMessage modify(ConfigurationVO after);

}
