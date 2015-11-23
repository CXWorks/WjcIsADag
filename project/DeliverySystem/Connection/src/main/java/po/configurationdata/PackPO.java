package po.configurationdata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import po.CommonPO;
import po.InfoEnum;
import po.InfoPO;
import po.configurationdata.enums.PackEnum;

public class PackPO extends InfoPO implements Serializable{
	private HashMap<PackEnum,Double> packPrice;
	
	
	public PackPO() {
		super(InfoEnum.PACK);
		this.packPrice=new HashMap();
	}
	public double getByType(PackEnum type){
		return this.packPrice.get(type);
	}
	public HashMap<PackEnum, Double> getPackPrice() {
		return packPrice;
	}
	public HashMap<PackEnum, Double> getClonedPackPrice(){
		HashMap<PackEnum, Double> ans=new HashMap<PackEnum, Double>();
		ans.putAll(packPrice);
		return ans;
	}
	//
	public void setPackPrice(HashMap<PackEnum, Double> toSet){
		this.packPrice.putAll(toSet);
	}
}
