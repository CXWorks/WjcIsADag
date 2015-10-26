package po.configurationdata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import po.configurationdata.enums.ConfigurationEnum;
import po.configurationdata.enums.PackEnum;

public class PackPO implements Serializable{
	private ConfigurationEnum ID;
	private Map<PackEnum,Double> packPrice;
	
	public PackPO() {
		// TODO Auto-generated constructor stub
		this.ID=ConfigurationEnum.PACK;
		this.packPrice=new HashMap();
		this.packPrice.put(PackEnum.WOOD, (double) 10);
		this.packPrice.put(PackEnum.PAPER, (double) 5);
		this.packPrice.put(PackEnum.PACKAGE, (double) 1);
		this.packPrice.put(PackEnum.OTHER, (double)0);
	}
	public double getByType(PackEnum type){
		return this.packPrice.get(type);
	}
	public Map<PackEnum, Double> getPackPrice() {
		return packPrice;
	}
	
}
