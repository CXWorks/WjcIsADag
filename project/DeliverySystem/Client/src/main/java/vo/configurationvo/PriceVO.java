package vo.configurationvo;

import java.util.HashMap;
import java.util.Map;

import po.InfoEnum;
import po.configurationdata.PricePO;
import po.orderdata.DeliverTypeEnum;

public class PriceVO extends ConfigurationVO{
	//TODO discuss how to build this with JR
	private HashMap<DeliverTypeEnum,Integer> price;
	public PriceVO(){
		super(InfoEnum.PRICE);
	}
	public int getByType(DeliverTypeEnum type){
		return price.get(type);
	}

	public void setByType(DeliverTypeEnum type,int newPrice){
		int origin=price.get(type);

		price.replace(type, newPrice);
		price.replace(DeliverTypeEnum.FAST, price.get(DeliverTypeEnum.FAST)*newPrice/origin);
		price.replace(DeliverTypeEnum.SLOW, price.get(DeliverTypeEnum.SLOW)*newPrice/origin);
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
