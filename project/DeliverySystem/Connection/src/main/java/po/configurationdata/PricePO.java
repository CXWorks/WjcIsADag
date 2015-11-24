package po.configurationdata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import po.CommonPO;
import po.InfoEnum;
import po.InfoPO;
import po.configurationdata.enums.DeliveryTypeEnum;
import po.configurationdata.enums.PackEnum;

public class PricePO extends InfoPO implements Serializable{
	private HashMap<DeliveryTypeEnum,Integer> price;
	public PricePO(){
		super(InfoEnum.PRICE);
		this.price=new HashMap<DeliveryTypeEnum, Integer>();
	}
	public PricePO(HashMap<DeliveryTypeEnum,Integer> price){
		super(InfoEnum.PRICE);
		this.price=price;
	}
	public HashMap<DeliveryTypeEnum, Integer> getPrice() {
		return price;
	}
	public int getByType(DeliveryTypeEnum type){
		return this.price.get(type);
	}
	public HashMap<DeliveryTypeEnum, Integer> getClonedPrice(){
		HashMap<DeliveryTypeEnum, Integer> ans=new HashMap<DeliveryTypeEnum, Integer>();
		ans.putAll(price);
		return ans;
	}
	public void setPrice(HashMap<DeliveryTypeEnum, Integer> toSet){
		this.price.putAll(toSet);
	}
}
