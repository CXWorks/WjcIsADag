package po.configurationdata;

import java.util.Map;

import po.configurationdata.enums.ConfigurationEnum;
import po.configurationdata.enums.DeliveryTypeEnum;

public class ProportionPO {
	private ConfigurationEnum ID;
	private Map<DeliveryTypeEnum,Integer> proportion;
}
