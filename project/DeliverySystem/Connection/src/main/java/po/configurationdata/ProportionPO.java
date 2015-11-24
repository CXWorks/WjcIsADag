package po.configurationdata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import po.CommonPO;
import po.InfoEnum;
import po.InfoPO;
import po.configurationdata.enums.DeliveryTypeEnum;

public class ProportionPO extends InfoPO implements Serializable{
	private HashMap<DeliveryTypeEnum,Integer> proportion;
	public ProportionPO(){
		super(InfoEnum.PROPORTION);
		this.proportion=new HashMap<DeliveryTypeEnum, Integer>();
	}
	public ProportionPO(HashMap<DeliveryTypeEnum,Integer> proportion){
		super(InfoEnum.PROPORTION);
		this.proportion=proportion;
	}
	public HashMap<DeliveryTypeEnum, Integer> getProportion() {
		return proportion;
	}
	public HashMap<DeliveryTypeEnum, Integer> getClonedProportion(){
		HashMap<DeliveryTypeEnum, Integer> ans=new HashMap<DeliveryTypeEnum, Integer>();
		ans.putAll(proportion);
		return ans;
	}
	public void setProportion(HashMap<DeliveryTypeEnum, Integer> toSet){
		this.proportion.putAll(toSet);
	}
	public int getByType(DeliveryTypeEnum type){
		return this.proportion.get(type);
	}
}
