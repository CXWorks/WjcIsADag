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
	private Distance distance;    					//计算城市距离
	private Money money;							//计算运费、包装费、收费比例
	private Salary salary;							//计算薪水策略
	public ConfigurationBLController(VOPOFactory vopoFactory){

		this.distance=new Distance(vopoFactory);	//新建距离成员

		this.money=new Money(vopoFactory);			//新建价格成员
		this.salary=new Salary(vopoFactory);		//新建薪水策略的帮助成员
	}

	/* (non-Javadoc)
	 * @see bl.blService.configurationblService.ConfigurationBLService#get(po.configurationdata.enums.ConfigurationEnum)
	 */
	public List<ConfigurationVO> get(InfoEnum type) {
		switch (type) {
		case PACK:			//包装费用
			return money.getPack();
		case PROPORTION:	//收费比例
			return money.getProportion();
		case PRICE:			//价格运费
			return money.getPrice();
		case SALARY:		//薪水数目
			return salary.getSalary();
		default:			//如果不是以上3者，应该是传错了，返回空
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.configurationblService.ConfigurationBLService#modify(vo.configurationvo.ConfigurationVO)
	 */
	public OperationMessage modify(ConfigurationVO after) {
		switch (after.getInfoEnum()) {
		case PRICE:			//价格
			PriceVO priceVO=(PriceVO)after;
			return money.modifyPrice(priceVO);
		case PROPORTION:	//收费比例
			ProportionVO proportionVO=(ProportionVO)after;
			return money.modifyProportion(proportionVO);
		case PACK:			//包装费用
			PackVO packVO=(PackVO)after;
			return money.modifyPack(packVO);
		case SALARY:		//薪水策略
			SalaryStrategyVO salaryStrategyVO=(SalaryStrategyVO)after;
			return salary.modify(salaryStrategyVO);
		default:			//返回失败信息，并给出理由
			return new OperationMessage(false, "unknown type");
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.configurationblService.ConfigurationBLService#getCity()
	 */
	@Override
	public List<City2DVO> getCity() {
		//获得城市距离
		return distance.getCityDistance();
	}

	/* (non-Javadoc)
	 * @see bl.blService.configurationblService.ConfigurationBLService#modifyCity(vo.configurationvo.City2DVO)
	 */
	@Override
	public OperationMessage modifyCity(City2DVO city2dvo) {
		//修改城市信息
		return distance.modifyCityDistance(city2dvo);
	}

	/* (non-Javadoc)
	 * @see bl.blService.configurationblService.ConfigurationBLService#newCity(vo.configurationvo.City2DVO)
	 */
	@Override
	public OperationMessage newCity(City2DVO city2dvo) {
		//新建城市信息
		return distance.newCity(city2dvo);
	}

	/* (non-Javadoc)
	 * @see bl.blService.configurationblService.ConfigurationBLService#deleteCity(vo.configurationvo.City2DVO)
	 */
	@Override
	public OperationMessage deleteCity(City2DVO city2dvo) {
		//删除城市信息
		return distance.deleteCity(city2dvo);
	}

}
