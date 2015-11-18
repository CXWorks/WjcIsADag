package po.configurationdata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import po.CommonPO;
import po.InfoPO;
import po.configurationdata.enums.DeliveryTypeEnum;

public class PricePO extends InfoPO implements Serializable{
	private HashMap<DeliveryTypeEnum,Integer> price;
	public PricePO(){
		this.price=new HashMap<DeliveryTypeEnum, Integer>();
		price.put(DeliveryTypeEnum.USUAL, 23);
		price.put(DeliveryTypeEnum.ECONOMIC, 15);
		price.put(DeliveryTypeEnum.FAST, 30);
	}
	public HashMap<DeliveryTypeEnum, Integer> getPrice() {
		return price;
	}
	public HashMap<DeliveryTypeEnum, Integer> getClonedPrice(){
		HashMap<DeliveryTypeEnum, Integer> ans=new HashMap<DeliveryTypeEnum, Integer>();
		ans.putAll(price);
		return ans;
	}
	
}
