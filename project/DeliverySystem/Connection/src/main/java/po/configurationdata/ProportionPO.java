package po.configurationdata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import po.CommonPO;
import po.InfoPO;
import po.configurationdata.enums.DeliveryTypeEnum;

public class ProportionPO extends InfoPO implements Serializable{
	private Map<DeliveryTypeEnum,Integer> proportion;
	public ProportionPO(){
		this.proportion=new HashMap<DeliveryTypeEnum, Integer>();
		this.proportion.put(DeliveryTypeEnum.USUAL, 23);
		proportion.put(DeliveryTypeEnum.ECONOMIC, 15);
		proportion.put(DeliveryTypeEnum.FAST, 30);
	}
	public Map<DeliveryTypeEnum, Integer> getProportion() {
		return proportion;
	}
	
}
