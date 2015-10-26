package vo.configurationvo;

import java.util.HashMap;
import java.util.Map;

import po.configurationdata.PackPO;
import po.configurationdata.enums.ConfigurationEnum;
import po.configurationdata.enums.PackEnum;

public class PackVO {
	private ConfigurationEnum ID;
	private Map<PackEnum,Double> packPrice;
	
	public PackVO(PackPO po){
		
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
