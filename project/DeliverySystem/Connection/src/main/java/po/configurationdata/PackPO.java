package po.configurationdata;

import java.io.Serializable;
import java.util.Map;

import po.configurationdata.enums.ConfigurationEnum;
import po.configurationdata.enums.PackEnum;

public class PackPO implements Serializable{
	private ConfigurationEnum ID;
	private Map<PackEnum,Double> packPrice;
	
}
