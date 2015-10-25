package po.configurationdata;

import java.io.Serializable;

import po.configurationdata.enums.ConfigurationEnum;

public class CityDistancePO implements Serializable{
	private ConfigurationEnum ID;
	private String city1;
	private String city2;
	public int distance;
}
