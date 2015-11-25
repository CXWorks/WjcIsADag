package po.configurationdata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import po.CommonPO;
import po.InfoEnum;
import po.InfoPO;
import po.orderdata.DeliverTypeEnum;

public class ProportionPO extends InfoPO implements Serializable{
	private HashMap<DeliverTypeEnum,Integer> proportion;
	public ProportionPO(){
		super(InfoEnum.PROPORTION);
		this.proportion=new HashMap<DeliverTypeEnum, Integer>();
	}
	public ProportionPO(HashMap<DeliverTypeEnum,Integer> proportion){
		super(InfoEnum.PROPORTION);
		this.proportion=proportion;
	}
	public HashMap<DeliverTypeEnum, Integer> getProportion() {
		return proportion;
	}
	public HashMap<DeliverTypeEnum, Integer> getClonedProportion(){
		HashMap<DeliverTypeEnum, Integer> ans=new HashMap<DeliverTypeEnum, Integer>();
		ans.putAll(proportion);
		return ans;
	}
	public void setProportion(HashMap<DeliverTypeEnum, Integer> toSet){
		this.proportion.putAll(toSet);
	}
	public int getByType(DeliverTypeEnum type){
		return this.proportion.get(type);
	}
}
