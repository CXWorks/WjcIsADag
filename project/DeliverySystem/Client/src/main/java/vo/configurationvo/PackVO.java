package vo.configurationvo;

import java.util.HashMap;
import java.util.Map;

import po.configurationdata.enums.ConfigurationEnum;
import po.configurationdata.enums.PackEnum;

public class PackVO {
	private ConfigurationEnum ID;
	private Map<PackEnum,Double> packPrice;
	
	public PackVO(){
		ID = ConfigurationEnum.PACK;
		packPrice = new HashMap<PackEnum,Double>();
		packPrice.put(PackEnum.WOOD , 3.0 );
		packPrice.put(PackEnum.PACKAGE , 2.0 );
		packPrice.put(PackEnum.PAPER , 1.0 );
		packPrice.put(PackEnum.OTHER , 0.0 );
	}
	
	public ConfigurationEnum getID() {
		return ID;
	}
	public void setID(ConfigurationEnum iD) {
		ID = iD;
	}
	public Map<PackEnum, Double> getPackPrice() {
		return packPrice;
	}
	public void setPackPrice(Map<PackEnum, Double> packPrice) {
		this.packPrice = packPrice;
	}
	
}
