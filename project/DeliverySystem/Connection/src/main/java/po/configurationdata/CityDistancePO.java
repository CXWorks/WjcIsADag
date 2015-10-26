package po.configurationdata;

import java.io.Serializable;

import po.configurationdata.enums.ConfigurationEnum;

public class CityDistancePO implements Serializable{
	private ConfigurationEnum ID;
	private String city1;
	private String city2;
	private int distance;
	public CityDistancePO(){
		this.ID=ConfigurationEnum.CITY_DISTANCE;
		this.city1="南京";
		this.city2="北京";
		this.distance=1000;
	}
	//
	public CityDistancePO(ConfigurationEnum id,String city1,String city2,int dis){
		this.ID=id;
		this.city1=city1;
		this.city2=city2;
		this.distance=dis;
	}
	//
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
