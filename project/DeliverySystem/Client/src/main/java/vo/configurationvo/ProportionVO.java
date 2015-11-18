package vo.configurationvo;

import java.util.HashMap;
import java.util.Map;

import po.InfoEnum;
import po.configurationdata.ProportionPO;
import po.configurationdata.enums.DeliveryTypeEnum;

public class ProportionVO extends ConfigurationVO{
	private HashMap<DeliveryTypeEnum,Integer> proportion;
	public ProportionVO(){
		super(InfoEnum.PROPORTION);
	}
	public int getByType(DeliveryTypeEnum type) {
		return proportion.get(type);
	}
	public ProportionVO(ProportionPO po){
		this();
		this.proportion=po.getClonedProportion();
	}
}
