package vo.configurationvo;

import java.util.Map;

import po.configurationdata.enums.ConfigurationEnum;
import po.configurationdata.enums.DeliveryTypeEnum;

public class ProportionVO {
	private ConfigurationEnum ID;
	private Map<DeliveryTypeEnum,Integer> proportion;
	public int getByType(DeliveryTypeEnum type) {
		return proportion.get(type);
	}
}
