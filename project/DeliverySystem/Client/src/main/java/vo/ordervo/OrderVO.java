package vo.ordervo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import po.FormEnum;
import po.FormStateEnum;
import po.orderdata.DeliverTypeEnum;
import po.orderdata.OrderPO;
import po.orderdata.PackingEnum;
import userinfo.UserInfo;
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
	private String receivePeople;
	private Calendar receiveDate;
	//
	private int len;
	private int wid;
	private int hei;
	private int V;
	private int W;

	public OrderVO(String formID,String createrID) {
		super(FormEnum.ORDER, FormStateEnum.CONSTRUCTED, formID,createrID);
	}
	public void calculateVolume(){
		StringTokenizer stringTokenizer=new StringTokenizer(volume, "*, ");
		len=Integer.parseInt(stringTokenizer.nextToken());
		wid=Integer.parseInt(stringTokenizer.nextToken());
		hei=Integer.parseInt(stringTokenizer.nextToken());
		V=len*wid*hei;
		W=Integer.parseInt(weight);
	}
	public int getV(){
		return V;
	}
	public int getFormIDInt(){
		return Integer.parseInt(formID);
	}
	public int getLen() {
		return len;
	}
	public int getWid() {
		return wid;
	}
	public int getHei() {
		return hei;
	}
	public int getW() {
		return W;
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
	public OrderVO(String formID, String nameFrom, String nameTo,
			String unitFrom, String unitTo, String addressFrom,
			String addressTo, String phoneNumFrom, String phoneNumTo,
			String telNumFrom, String telNumTo, String goodsNum,
			String goodsName, String weight, String volume, String money,
			String goodsType, DeliverTypeEnum type, PackingEnum pack,String createrID) {
		this(formID,createrID);
		this.nameFrom = nameFrom;
		this.nameTo = nameTo;
		this.addressFrom = addressFrom;
		this.addressTo = addressTo;
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
		this.goodsType = goodsType;
		this.type = type;
		this.pack = pack;
	}
	//
	

	//
	public OrderVO(OrderPO po) {
		// deep clone
		this(po.getFormID(), po.getNameFrom(), po.getNameTo(),
				po.getUnitFrom(), po.getUnitTo(), po.getAddressFrom(), po
						.getAddressTo(), po.getPhoneNumFrom(), po
						.getPhoneNumTo(), po.getTelNumFrom(), po.getTelNumTo(),
				po.getGoodsNum(), po.getGoodsName(), po.getWeight(), po
						.getVolume(),  po.getGoodsType(),po.getMoney(), po
						.getType(), po.getPack(),po.getReceivePeople(),po.getReceiveDate(),po.getCreaterID());

	}
	//
	

	//
	public OrderPO toPO() {
		OrderPO orderPO= new OrderPO(formID, nameFrom, nameTo, unitFrom, unitTo,
				addressFrom, addressTo, phoneNumFrom, phoneNumTo, telNumFrom,
				telNumTo, goodsNum, goodsName, weight, volume, 
				money,goodsType, type.name(), pack.name(), new ArrayList<String>(), null,receivePeople,receiveDate,createrID);
		orderPO.setCache_OperatorID(UserInfo.getUserID());
		return orderPO;
	}

	/**
	 * @param state
	 * @param formID
	 * @param nameFrom
	 * @param nameTo
	 * @param addressFrom
	 * @param addressTo
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
	 * @param goodsType
	 * @param money
	 * @param type2
	 * @param pack
	 * @param receivePeople
	 * @param receiveDate
	 */
	public OrderVO(String formID,
			String nameFrom, String nameTo, String addressFrom,
			String addressTo, String unitFrom, String unitTo,
			String phoneNumFrom, String phoneNumTo, String telNumFrom,
			String telNumTo, String goodsNum, String goodsName, String weight,
			String volume, String goodsType, String money,
			DeliverTypeEnum type2, PackingEnum pack, String receivePeople,
			Calendar receiveDate,String createrID) {
		this(formID,createrID);
		this.nameFrom = nameFrom;
		this.nameTo = nameTo;
		this.addressFrom = addressFrom;
		this.addressTo = addressTo;
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
		this.goodsType = goodsType;
		this.money = money;
		type = type2;
		this.pack = pack;
		this.receivePeople = receivePeople;
		this.receiveDate = receiveDate;
	}

	public String getAddressFrom() {
		return addressFrom;
	}

	public String getAddressTo() {
		return addressTo;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public String getGoodsNum() {
		return goodsNum;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public String getMoney() {
		return money;
	}

	public String getNameFrom() {
		return nameFrom;
	}

	public String getNameTo() {
		return nameTo;
	}

	public PackingEnum getPack() {
		return pack;
	}

	public String getPhoneNumFrom() {
		return phoneNumFrom;
	}

	public String getPhoneNumTo() {
		return phoneNumTo;
	}

	public String getTelNumFrom() {
		return telNumFrom;
	}

	public String getTelNumTo() {
		return telNumTo;
	}

	public DeliverTypeEnum getType() {
		return type;
	}

	public String getUnitFrom() {
		return unitFrom;
	}

	public String getUnitTo() {
		return unitTo;
	}

	public String getVolume() {
		return volume;
	}

	public String getWeight() {
		return weight;
	}
	//

	public String getReceivePeople() {
		return receivePeople;
	}

	public void setReceivePeople(String receivePeople) {
		this.receivePeople = receivePeople;
	}

	public Calendar getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Calendar receiveDate) {
		this.receiveDate = receiveDate;
	}

	/* (non-Javadoc)
	 * @see vo.FormVO#getMainInfo()
	 */
	@Override
	public String getMainInfo() {
		return nameTo+" "+nameFrom+" "+goodsName;
	}
	
}
