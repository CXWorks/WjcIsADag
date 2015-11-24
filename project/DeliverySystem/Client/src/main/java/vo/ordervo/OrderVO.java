package vo.ordervo;

import java.util.ArrayList;

import po.FormEnum;
import po.FormStateEnum;
import po.orderdata.DeliverTypeEnum;
import po.orderdata.OrderPO;
import po.orderdata.PackingEnum;
import vo.FormVO;

/**
 * 
 * @author CCharles_Meng 2015/10/24
 */

public class OrderVO extends FormVO {
	private String nameFrom;
	private String nameTo;
	private String addressFrom;
	private String addressTo;
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
	private String goodsType;
	private String money;
	private DeliverTypeEnum type;
	private PackingEnum pack;
	public OrderVO(String formID) {
		super(FormEnum.ORDER,FormStateEnum.CONSTRUCTED,formID);
	}
	
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
	 * @param goodstype
	 * @param type
	 */
	public OrderVO(String formID,String nameFrom, String nameTo, String unitFrom,
			String unitTo,  String addressFrom,String addressTo,String phoneNumFrom, String phoneNumTo,
			String telNumFrom, String telNumTo, String goodsNum,
			String goodsName, String weight, String volume,String money, String goodsType,
			DeliverTypeEnum type ,PackingEnum pack) {
		this(formID);
		this.nameFrom = nameFrom;
		this.nameTo = nameTo;
		this.addressFrom=addressFrom;
		this.addressTo=addressTo;
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
		this.money=money;
		this.goodsType = goodsType;
		this.type = type;
		this.pack=pack;
	}

	//
	public OrderVO(OrderPO po) {
		// deep clone
		this(po.getFormID(),po.getNameFrom(), po.getNameTo(), po.getUnitFrom(),
				po.getUnitTo(), po.getPhoneNumFrom(), po.getPhoneNumTo(), po
						.getTelNumFrom(), po.getTelNumTo(), po.getGoodsNum(),
				po.getGoodsName(), po.getWeight(), po.getVolume(), po.getAddressFrom(),po.getAddressTo(),
						po.getMoney(),po.getGoodsType(), po.getType(),po.getPack());

	}
	//
	public OrderPO toPO(){
		return new OrderPO(formID, nameFrom, nameTo, unitFrom, unitTo, addressFrom, addressTo, phoneNumFrom, phoneNumTo, telNumFrom, telNumTo, goodsNum, goodsName, weight, volume, money, goodsType, type.name(), pack.name(), null, null);
	}
}
