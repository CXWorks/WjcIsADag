package po.configurationdata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import po.CommonPO;
import po.InfoEnum;
import po.InfoPO;
import po.configurationdata.enums.PackEnum;
import po.orderdata.DeliverTypeEnum;

public class PricePO extends InfoPO implements Serializable{
	private HashMap<DeliverTypeEnum,Integer> price;
	public PricePO(){
		super(InfoEnum.PRICE);
		this.price=new HashMap<DeliverTypeEnum, Integer>();
	}
	public PricePO(HashMap<DeliverTypeEnum,Integer> price){
		super(InfoEnum.PRICE);
		this.price=price;
	}
	public HashMap<DeliverTypeEnum, Integer> getPrice() {
		return price;
	}
	public int getByType(DeliverTypeEnum deliverTypeEnum){
		return this.price.get(deliverTypeEnum);
	}
	public HashMap<DeliverTypeEnum, Integer> getClonedPrice(){
		System.out.println(price.get(DeliverTypeEnum.SLOW));
		HashMap<DeliverTypeEnum, Integer> ans=new HashMap<DeliverTypeEnum, Integer>();
		ans.putAll(price);
		return ans;
	}
	public void setPrice(HashMap<DeliverTypeEnum, Integer> toSet){
		this.price.putAll(toSet);
	}
}
