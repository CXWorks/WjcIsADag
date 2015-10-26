package po.configurationdata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import po.configurationdata.enums.ConfigurationEnum;
import po.configurationdata.enums.DeliveryTypeEnum;

public class PricePO implements Serializable{
	private ConfigurationEnum ID;
	private Map<DeliveryTypeEnum,Integer> price;
	public PricePO(){
		this.ID=ConfigurationEnum.PRICE;
		this.price=new HashMap<DeliveryTypeEnum, Integer>();
		price.put(DeliveryTypeEnum.USUAL, 23);
		price.put(DeliveryTypeEnum.ECONOMIC, 15);
		price.put(DeliveryTypeEnum.FAST, 30);
	}
	public Map<DeliveryTypeEnum, Integer> getPrice() {
		return price;
	}
	
}
