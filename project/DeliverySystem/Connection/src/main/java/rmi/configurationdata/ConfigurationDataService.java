package rmi.configurationdata;
import java.util.ArrayList;

import message.OperationMessage;
import po.configurationdata.*;
/**
 * 
 * @author cxworks
 *2015/10/25
 */
public interface ConfigurationDataService {
	
	public ArrayList<CityDistancePO> getCityDistance();
	
	public OperationMessage modifyCityDistance(CityDistancePO after);
	
	public ArrayList<SalaryStrategyPO> getSalaryStrategy();
	
	public OperationMessage modifySalaryStrategy(SalaryStrategyPO salaryStrategy);
	
	public ArrayList<PackPO> getPack();
	
	public OperationMessage modifyPack(PackPO pack);
	
	public PricePO getPrice();
	
	public OperationMessage modifyPrice(PricePO price);
	
	public ProportionPO getProportion();
	
	public OperationMessage modifyProportion(ProportionPO proportion);
	
}
