package rmiImpl.configurationdata;

import java.util.ArrayList;

import message.OperationMessage;
import po.configurationdata.CityDistancePO;
import po.configurationdata.PackPO;
import po.configurationdata.PricePO;
import po.configurationdata.ProportionPO;
import po.configurationdata.SalaryStrategyPO;
import rmi.configurationdata.ConfigurationDataService;
/**
 * 目前全是stub
 * @author cxworks
 *2015/10/25
 */
public class ConfigurationDataImpl implements ConfigurationDataService {

	public ArrayList<CityDistancePO> getCityDistance() {
		// TODO Auto-generated method stub
		ArrayList<CityDistancePO> result=new ArrayList<CityDistancePO>();
		CityDistancePO stub=new CityDistancePO();
		result.add(stub);
		return result;
	}

	public OperationMessage modifyCityDistance(CityDistancePO after) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public ArrayList<SalaryStrategyPO> getSalaryStrategy() {
		// TODO Auto-generated method stub
		ArrayList<SalaryStrategyPO> result=new ArrayList<SalaryStrategyPO>();
		SalaryStrategyPO stub=new SalaryStrategyPO();
		result.add(stub);
		return result;
	}

	public OperationMessage modifySalaryStrategy(SalaryStrategyPO salaryStrategy) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public ArrayList<PackPO> getPack() {
		// TODO Auto-generated method stub
		ArrayList<PackPO> result=new ArrayList<PackPO>();
		PackPO stub=new PackPO();
		result.add(stub);
		return result;
	}

	public OperationMessage modifyPack(PackPO pack) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public PricePO getPrice() {
		// TODO Auto-generated method stub
		
		return new PricePO();
	}

	public OperationMessage modifyPrice(PricePO price) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public ProportionPO getProportion() {
		// TODO Auto-generated method stub
		return new ProportionPO();
	}

	public OperationMessage modifyProportion(ProportionPO proportion) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

}
