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
	/**
	 * @param infoEnum
	 * @param city1
	 * @param city2
	 * @param distance
	 */
	public CityDistanceVO(String city1, String city2,
			int distance) {
		super(InfoEnum.CITY_DISTANCE);
		this.city1 = city1;
		this.city2 = city2;
		this.distance = distance;
	}
	
	public CityDistancePO toPO(){
		return new CityDistancePO(city1, city2, distance);
	}
}
