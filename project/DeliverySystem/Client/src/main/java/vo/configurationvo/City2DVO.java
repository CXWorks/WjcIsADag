package vo.configurationvo;

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
}
