package vo.ordervo;

import po.orderdata.TypeEnum;
import vo.FormVO;

/**
 * 
 * @author CCharles_Meng
 *2015/10/24
 */

public class OrderVO extends FormVO{
	  private String nameFrom;
	  private String nameTo;
	  private String location;
	  private String loacal;
	  private String unitFrom;
	  private String unitTo;
	  private String phoneNumFrom;
	  private String phoneNumTo;
	  private String telNumFrom;
	  private String telNumTo;
	  private String goodsNum;
	  private String goodsName;
	  private String weight;
	  private String volume;
	  private String money;
	  private TypeEnum type;
	  public OrderVO(){}
	  
	  /**
	 * @param nameFrom
	 * @param nameTo
	 * @param location
	 * @param loacal
	 * @param unitFrom
	 * @param unitTo
	 * @param phoneNumFrom
	 * @param phoneNumTo
	 * @param telNumFrom
	 * @param telNumTo
	 * @param goodsNum
	 * @param goodsName
	 * @param weight
	 * @param volume
	 * @param money
	 * @param type
	 */
	public OrderVO(String nameFrom, String nameTo, String location,
			String loacal, String unitFrom, String unitTo, String phoneNumFrom,
			String phoneNumTo, String telNumFrom, String telNumTo,
			String goodsNum, String goodsName, String weight, String volume,
			String money, TypeEnum type) {
		super();
		this.nameFrom = nameFrom;
		this.nameTo = nameTo;
		this.location = location;
		this.loacal = loacal;
		this.unitFrom = unitFrom;
		this.unitTo = unitTo;
		this.phoneNumFrom = phoneNumFrom;
		this.phoneNumTo = phoneNumTo;
		this.telNumFrom = telNumFrom;
		this.telNumTo = telNumTo;
		this.goodsNum = goodsNum;
		this.goodsName = goodsName;
		this.weight = weight;
		this.volume = volume;
		this.money = money;
		this.type = type;
	}
}
