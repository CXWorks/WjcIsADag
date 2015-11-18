package vo.configurationvo;


import po.InfoEnum;
import po.configurationdata.CityDistancePO;

public class CityDistanceVO extends ConfigurationVO{
	private String city1;
	private String city2;
	private int distance;
	public CityDistanceVO(CityDistancePO po){
		super(InfoEnum.CITY_DISTANCE);
		this.city1=po.getCity1();
		this.city2=po.getCity2();
		this.distance=po.getDistance();
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
