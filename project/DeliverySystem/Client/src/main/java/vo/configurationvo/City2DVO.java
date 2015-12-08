package vo.configurationvo;

import java.math.BigDecimal;

import po.InfoEnum;
import po.configurationdata.City2DPO;

/** 
 * Client//vo.configurationvo//City2DVO.java
 * @author CXWorks
 * @date 2015年11月24日 下午10:07:24
 * @version 1.0 
 */
public class City2DVO extends ConfigurationVO {
	private City2DVO(){
		super(InfoEnum.CITY_2D);
	} 
	//
	private String name;
	
	private double x;

	private double y;
	
	
	//误差
	private static final double ERROR = 0.05;
	//比例尺 1：100
	private static final double SCALE = 100;
	
	public City2DVO(String name,double x,double y){
		this();
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public City2DVO(City2DPO po){
		this(po.getName(), po.getX(), po.getY());
	}
	
	public City2DPO toPO(){
		return new City2DPO(name, x, y);
	}
	//
	public double distance(City2DVO target){
		double dis=Math.sqrt(Math.pow((this.x-target.x), 2)+Math.pow((this.y-target.y), 2))*SCALE;
		BigDecimal ans=new BigDecimal(dis);
		return ans.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
	}
	public String getXY(){
		return Double.toString(x)+","+Double.toString(y);
	}
}
