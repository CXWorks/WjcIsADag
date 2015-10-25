package blService.configurationblService;

import java.util.ArrayList;

import po.configurationdata.PackPO;
import po.configurationdata.PricePO;
import message.OperationMessage;
import vo.configurationvo.*;

public interface ConfigurationblService {
	
	public ArrayList<CityDistanceVO> getCityDistance();
	
	public OperationMessage	modifyCityDistance(CityDistanceVO after);
	
	public ArrayList<SalaryStrategyVO> getSalaryStrategy();
	
	public OperationMessage modifySalaryStrategy(ArrayList<SalaryStrategyVO> after);
	
	public PackVO getPack();
	
	public OperationMessage modifyPack(PackVO after);
	
	public PriceVO getPrice();
	
	public OperationMessage modifyPrice(PriceVO after);
	
	public ProportionVO getProportion();
	
	public OperationMessage modifyProportion(ProportionVO after);
	
}
