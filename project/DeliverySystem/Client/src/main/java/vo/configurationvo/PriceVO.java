package vo.configurationvo;

import java.util.Map;

import po.configurationdata.PricePO;
import po.configurationdata.enums.ConfigurationEnum;
import po.configurationdata.enums.DeliveryTypeEnum;

public class PriceVO extends ConfigurationVO{
	private Map<DeliveryTypeEnum,Integer> price;
	public int getByType(DeliveryTypeEnum type){
		return price.get(type);
	}
	public PriceVO(PricePO po){
		this.price=po.getPrice();
	}
}
