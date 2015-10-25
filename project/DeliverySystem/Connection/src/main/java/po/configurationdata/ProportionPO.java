package po.configurationdata;

import java.io.Serializable;
import java.util.Map;

import po.configurationdata.enums.ConfigurationEnum;
import po.configurationdata.enums.DeliveryTypeEnum;

public class ProportionPO implements Serializable{
	private ConfigurationEnum ID;
	private Map<DeliveryTypeEnum,Integer> proportion;
}
