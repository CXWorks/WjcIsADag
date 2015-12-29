package vo.logisticsvo;

import java.util.ArrayList;
import java.util.List;

import po.InfoEnum;
import po.orderdata.DeliverTypeEnum;
import po.orderdata.OrderPO;
import vo.InfoVO;

/**
 * 
 * @author CCharles_Meng
 *2015/10/24
 */

public class LogisticsVO extends InfoVO{
	  private String nameFrom;
	  private String nameTo;
	  private ArrayList<String> location;
	  private ArrayList<String> time;
	  private String local;
	  private String phoneNumTo;
	  private String goodsNum;
	  private String goodsName;
	  private DeliverTypeEnum type;
	  public LogisticsVO(){
		  super(InfoEnum.LOGISTICS);
	  }
	  //
	/**
	 * @param infoEnum
	 * @param nameFrom
	 * @param nameTo
	 * @param location
	 * @param time
	 * @param local
	 * @param phoneNumTo
	 * @param goodsNum
	 * @param goodsName
	 * @param type
	 */
	public LogisticsVO(String nameFrom, String nameTo,
			ArrayList<String> location, ArrayList<String> time, String local,
			String phoneNumTo, String goodsNum, String goodsName,
			DeliverTypeEnum type) {
		super(InfoEnum.LOGISTICS);
		this.nameFrom = nameFrom;
		this.nameTo = nameTo;
		this.location = location;
		this.time = time;
		this.local = local;
		this.phoneNumTo = phoneNumTo;
		this.goodsNum = goodsNum;
		this.goodsName = goodsName;
		this.type = type;
	}
	//
	public LogisticsVO(OrderPO order,ArrayList<String> location, ArrayList<String> time){
		this(order.getNameFrom(), order.getNameTo(), location, time, null, order.getPhoneNumTo(), order.getGoodsNum(), order.getGoodsName(), order.getType());
	}
	public List<String> getLocation() {
		return location;
	}
	public List<String> getTime() {
		return time;
	}
	public String getLocal() {
		return local;
	}
	public void setLocation(ArrayList<String> location) {
		this.location = location;
	}
	public void setTime(ArrayList<String> time) {
		this.time = time;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getNameFrom() {
		return nameFrom;
	}
	public String getNameTo() {
		return nameTo;
	}
	public String getPhoneNumTo() {
		return phoneNumTo;
	}
	public String getGoodsNum() {
		return goodsNum;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public DeliverTypeEnum getType() {
		return type;
	}
}
