package bl.blImpl.configurationbl;

import java.util.List;

import message.OperationMessage;
import po.InfoEnum;
import rmi.configurationdata.ConfigurationDataService;
import vo.configurationvo.City2DVO;
import vo.configurationvo.CityDistanceVO;
import vo.configurationvo.ConfigurationVO;
import vo.configurationvo.PackVO;
import vo.configurationvo.PriceVO;
import vo.configurationvo.ProportionVO;
import vo.configurationvo.SalaryStrategyVO;
import bl.blService.configurationblService.ConfigurationBLService;
import bl.clientNetCache.CacheHelper;
import tool.vopo.VOPOFactory;

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
		
		this.distance=new Distance(vopoFactory);
		this.money=new Money(vopoFactory);
		this.salary=new Salary(vopoFactory);
	}

	/* (non-Javadoc)
	 * @see bl.blService.configurationblService.ConfigurationBLService#get(po.configurationdata.enums.ConfigurationEnum)
	 */
	public List<ConfigurationVO> get(InfoEnum type) {
		switch (type) {
		case PACK:
			return money.getPack();
		case PROPORTION:
			return money.getProportion();
		case PRICE:
			return money.getPrice();
		case SALARY:
			return salary.getSalary();
		default:
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.configurationblService.ConfigurationBLService#modify(vo.configurationvo.ConfigurationVO)
	 */
	public OperationMessage modify(ConfigurationVO after) {
		switch (after.getInfoEnum()) {
		case PRICE:
			PriceVO priceVO=(PriceVO)after;
			return money.modifyPrice(priceVO);
		case PROPORTION:
			ProportionVO proportionVO=(ProportionVO)after;
			return money.modifyProportion(proportionVO);
		case PACK:
			PackVO packVO=(PackVO)after;
			return money.modifyPack(packVO);
		case SALARY:
			SalaryStrategyVO salaryStrategyVO=(SalaryStrategyVO)after;
			return salary.modify(salaryStrategyVO);
		default:
			return new OperationMessage(false, "unknown type");
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.configurationblService.ConfigurationBLService#getCity()
	 */
	@Override
	public List<City2DVO> getCity() {
		return distance.getCityDistance();
	}

	/* (non-Javadoc)
	 * @see bl.blService.configurationblService.ConfigurationBLService#modifyCity(vo.configurationvo.City2DVO)
	 */
	@Override
	public OperationMessage modifyCity(City2DVO city2dvo) {
		return distance.modifyCityDistance(city2dvo);
	}

	/* (non-Javadoc)
	 * @see bl.blService.configurationblService.ConfigurationBLService#newCity(vo.configurationvo.City2DVO)
	 */
	@Override
	public OperationMessage newCity(City2DVO city2dvo) {
		return distance.newCity(city2dvo);
	}

	/* (non-Javadoc)
	 * @see bl.blService.configurationblService.ConfigurationBLService#deleteCity(vo.configurationvo.City2DVO)
	 */
	@Override
	public OperationMessage deleteCity(City2DVO city2dvo) {
		return distance.deleteCity(city2dvo);
	}

}
