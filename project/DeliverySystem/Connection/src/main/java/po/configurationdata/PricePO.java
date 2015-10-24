package po.configurationdata;

import java.util.Map;

import po.configurationdata.enums.ConfigurationEnum;
import po.configurationdata.enums.DeliveryTypeEnum;

public class PricePO {
	private ConfigurationEnum ID;
	private Map<DeliveryTypeEnum,Integer> price;
}
