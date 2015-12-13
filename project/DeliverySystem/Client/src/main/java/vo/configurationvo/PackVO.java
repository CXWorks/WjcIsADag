package vo.configurationvo;

import java.util.HashMap;
import java.util.Map;

import po.InfoEnum;
import po.configurationdata.PackPO;
import po.configurationdata.enums.PackEnum;
import userinfo.UserInfo;

public class PackVO extends ConfigurationVO{
	private HashMap<PackEnum,Double> packPrice;
	public PackVO(){
		super(InfoEnum.PACK);
	}
	
	public PackVO(PackPO po){
		this();
		this.packPrice=po.getClonedPackPrice();
	}
	public void setByType(PackEnum packEnum,double newPrice){
		packPrice.replace(packEnum, newPrice);
	}
	
	public double getByType(PackEnum type){
		return packPrice.get(type);
	}
	
	public PackPO toPO(){
		PackPO temp=new PackPO();
		temp.setPackPrice(packPrice);
		temp.setCache_OperatorID(UserInfo.getUserID());
		return temp;
	}
}
