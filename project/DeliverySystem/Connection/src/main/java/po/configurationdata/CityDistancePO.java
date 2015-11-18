package po.configurationdata;

import java.io.Serializable;

import po.CommonPO;
import po.InfoPO;

public class CityDistancePO extends InfoPO implements Serializable{
	private String city1;
	private String city2;
	private int distance;
	public CityDistancePO(){
		this.city1="南京";
		this.city2="北京";
		this.distance=1000;
	}
	//
	public CityDistancePO(String city1,String city2,int dis){
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
