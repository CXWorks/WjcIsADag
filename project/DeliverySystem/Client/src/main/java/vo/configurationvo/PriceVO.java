package vo.configurationvo;

import java.util.HashMap;
import java.util.Map;

import po.InfoEnum;
import po.configurationdata.PricePO;
import po.configurationdata.enums.DeliveryTypeEnum;

public class PriceVO extends ConfigurationVO{
	//TODO discuss how to build this with JR
	private HashMap<DeliveryTypeEnum,Integer> price;
	public PriceVO(){
		super(InfoEnum.PRICE);
	}
	public int getByType(DeliveryTypeEnum type){
		return price.get(type);
	}
	public PriceVO(PricePO po){
		this();
		this.price=po.getClonedPrice();
	}
	//
	public PricePO toPO(){
		PricePO temp=new PricePO();
		temp.setPrice(price);
		return temp;
	}
}
