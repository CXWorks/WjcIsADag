package po.configurationdata;

import java.util.ArrayList;

import po.InfoEnum;
import po.InfoPO;

public class City2DPO extends InfoPO {
	
	private String name;
	
	private double x;

	private double y;
	
	private City2DPO(){
		super(InfoEnum.CITY_2D);
	}
	
	//误差
	private static final double ERROR = 0.05;
	//比例尺 1：100
	private static final double SCALE = 100;
	
	public City2DPO(String name,double x,double y){
		this();
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public double distance(City2DPO target){
		return Math.sqrt(Math.pow((this.getX()-target.x), 2)+Math.pow((this.getY()-target.y), 2))*SCALE;
		
	}
	
	public String getName() {
		return name;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
