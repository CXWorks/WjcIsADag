package blImpl.configurationbl;

import java.util.ArrayList;

import rmi.configurationdata.ConfigurationDataService;
import message.OperationMessage;
import vo.configurationvo.CityDistanceVO;
import vo.configurationvo.PackVO;
import vo.configurationvo.PriceVO;
import vo.configurationvo.ProportionVO;
import vo.configurationvo.SalaryStrategyVO;
import blService.configurationblService.ConfigurationblService;

/** 
 * Client//blImpl.configurationbl//ConfigurationblImpl.java
 * @author CXWorks
 * @date 2015年10月25日 下午4:52:56
 * @version 1.0 
 */
public class ConfigurationblImpl implements ConfigurationblService {
	ConfigurationDataService configurationDataImpl;

	/* (non-Javadoc)
	 * @see blService.configurationblService.ConfigurationblService#getCityDistance()
	 */
	public ArrayList<CityDistanceVO> getCityDistance() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see blService.configurationblService.ConfigurationblService#modifyCityDistance(vo.configurationvo.CityDistanceVO)
	 */
	public OperationMessage modifyCityDistance(CityDistanceVO after) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see blService.configurationblService.ConfigurationblService#getSalaryStrategy()
	 */
	public ArrayList<SalaryStrategyVO> getSalaryStrategy() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see blService.configurationblService.ConfigurationblService#modifySalaryStrategy(java.util.ArrayList)
	 */
	public OperationMessage modifySalaryStrategy(
			ArrayList<SalaryStrategyVO> after) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see blService.configurationblService.ConfigurationblService#getPack()
	 */
	public PackVO getPack() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see blService.configurationblService.ConfigurationblService#modifyPack(vo.configurationvo.PackVO)
	 */
	public OperationMessage modifyPack(PackVO after) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see blService.configurationblService.ConfigurationblService#getPrice()
	 */
	public PriceVO getPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see blService.configurationblService.ConfigurationblService#modifyPrice(vo.configurationvo.PriceVO)
	 */
	public OperationMessage modifyPrice(PriceVO after) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see blService.configurationblService.ConfigurationblService#getProportion()
	 */
	public ProportionVO getProportion() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see blService.configurationblService.ConfigurationblService#modifyProportion(vo.configurationvo.ProportionVO)
	 */
	public OperationMessage modifyProportion(ProportionVO after) {
		// TODO Auto-generated method stub
		return null;
	}

}
