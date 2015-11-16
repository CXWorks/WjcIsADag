package po.configurationdata;

import java.util.ArrayList;

public class City2DPO {
	
	private double x;

	private double y;
	
	//误差
	private static final double ERROR = 0.05;
	//比例尺 1：100
	private static final double SCALE = 100;
	
	public City2DPO(double x,double y){
		this.x = x;
		this.y = y;
	}
	
	public double distance(City2DPO target){
		return Math.sqrt(Math.pow((this.getX()-target.x), 2)+Math.pow((this.getY()-target.y), 2))*SCALE;
		
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
