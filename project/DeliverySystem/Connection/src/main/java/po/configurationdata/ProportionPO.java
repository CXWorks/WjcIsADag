package po.configurationdata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import po.CommonPO;
import po.InfoPO;
import po.configurationdata.enums.DeliveryTypeEnum;

public class ProportionPO extends InfoPO implements Serializable{
	private HashMap<DeliveryTypeEnum,Integer> proportion;
	public ProportionPO(){
		this.proportion=new HashMap<DeliveryTypeEnum, Integer>();
		this.proportion.put(DeliveryTypeEnum.USUAL, 23);
		proportion.put(DeliveryTypeEnum.ECONOMIC, 15);
		proportion.put(DeliveryTypeEnum.FAST, 30);
	}
	public HashMap<DeliveryTypeEnum, Integer> getProportion() {
		return proportion;
	}
	public HashMap<DeliveryTypeEnum, Integer> getClonedProportion(){
		HashMap<DeliveryTypeEnum, Integer> ans=new HashMap<DeliveryTypeEnum, Integer>();
		ans.putAll(proportion);
		return ans;
	}
}
