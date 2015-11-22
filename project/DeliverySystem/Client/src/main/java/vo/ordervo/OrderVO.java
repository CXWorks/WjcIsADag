package vo.ordervo;

import po.FormEnum;
import po.orderdata.DeliverTypeEnum;
import po.orderdata.OrderPO;
import vo.FormVO;

/**
 * 
 * @author CCharles_Meng 2015/10/24
 */

public class OrderVO extends FormVO {
	private String nameFrom;
	private String nameTo;
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
	private DeliverTypeEnum type;

	public OrderVO() {
		super(FormEnum.ORDER);
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
	 * @param type
	 */
	public OrderVO(String nameFrom, String nameTo, String unitFrom,
			String unitTo, String phoneNumFrom, String phoneNumTo,
			String telNumFrom, String telNumTo, String goodsNum,
			String goodsName, String weight, String volume, String money,
			DeliverTypeEnum type) {
		this();
		this.nameFrom = nameFrom;
		this.nameTo = nameTo;
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

	//
	public OrderVO(OrderPO po) {
		// deep clone
		this(po.getNameFrom(), po.getNameTo(), po.getUnitFrom(),
				po.getUnitTo(), po.getPhoneNumFrom(), po.getPhoneNumTo(), po
						.getTelNumFrom(), po.getTelNumTo(), po.getGoodsNum(),
				po.getGoodsName(), po.getWeight(), po.getVolume(), po
						.getMoney(), po.getType());

	}
}
