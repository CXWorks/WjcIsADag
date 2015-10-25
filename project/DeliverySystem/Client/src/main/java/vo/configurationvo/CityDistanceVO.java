package vo.configurationvo;


import po.configurationdata.CityDistancePO;
import po.configurationdata.enums.ConfigurationEnum;

public class CityDistanceVO {
	private ConfigurationEnum ID;
	private String city1;
	private String city2;
	private int distance;
	public CityDistanceVO(CityDistancePO po){
		this.ID=po.getID();
		this.city1=po.getCity1();
		this.city2=po.getCity2();
		this.distance=po.getDistance();
	}
	public ConfigurationEnum getID() {
		return ID;
	}
	public String getCity1() {
		return city1;
	}
	public String getCity2() {
		return city2;
	}
	public int getDistance() {
		return distance;
	}
	
}
