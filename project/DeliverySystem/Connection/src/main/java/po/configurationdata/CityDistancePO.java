package po.configurationdata;

import java.io.Serializable;

import po.CommonPO;
import po.InfoEnum;
import po.InfoPO;

public class CityDistancePO extends InfoPO implements Serializable{
	private String city1;
	private String city2;
	private int distance;
	private CityDistancePO(){
		super(InfoEnum.CITY_DISTANCE);
	}
	//
	public CityDistancePO(String city1,String city2,int dis){
		this();
		this.city1=city1;
		this.city2=city2;
		this.distance=dis;
	}
	//
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
