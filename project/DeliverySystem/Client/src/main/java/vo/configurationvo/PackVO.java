package vo.configurationvo;

import java.util.HashMap;
import java.util.Map;

import po.configurationdata.PackPO;
import po.configurationdata.enums.ConfigurationEnum;
import po.configurationdata.enums.PackEnum;

public class PackVO extends ConfigurationVO{
	private Map<PackEnum,Double> packPrice;
	
	public PackVO(PackPO po){
		this.packPrice=po.getPackPrice();
	}
	
	public double getByType(PackEnum type){
		return packPrice.get(type);
	}
	
}
