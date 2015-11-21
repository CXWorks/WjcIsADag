package po.orderdata;

import java.io.Serializable;
import java.util.ArrayList;

import po.FormPO;

public class OrderPO extends FormPO implements Serializable {
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

	private ArrayList<String> FormIDs;
	private String targetHallID;
	public OrderPO(){}
	
	public OrderPO(String nameFrom, String nameTo, String unitFrom,
			String unitTo, String phoneNumFrom, String phoneNumTo,
			String telNumFrom, String telNumTo, String goodsNum,
			String goodsName, String weight, String volume, String money,
			String type, ArrayList<String> formIDs, String targetHallID) {
		super();
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
		this.setType(type);
		FormIDs = formIDs;
		this.targetHallID = targetHallID;
	}

	//
	public String getNameFrom() {
		return nameFrom;
	}

	public String getNameTo() {
		return nameTo;
	}

	public String getUnitFrom() {
		return unitFrom;
	}

	public String getUnitTo() {
		return unitTo;
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

	public String getGoodsNum() {
		return goodsNum;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public String getWeight() {
		return weight;
	}

	public String getVolume() {
		return volume;
	}

	public String getMoney() {
		return money;
	}

	public DeliverTypeEnum getType() {
		return type;
	}
	
	public void setType(String type) {
		if (type.equalsIgnoreCase("SLOW"))
			this.type = DeliverTypeEnum.SLOW;
		else if (type.equalsIgnoreCase("NORMAL"))
			this.type = DeliverTypeEnum.NORMAL;
		else if (type.equalsIgnoreCase("FAST"))
			this.type = DeliverTypeEnum.FAST;
	}
	
	public ArrayList<String> getFormIDs() {
		return FormIDs;
	}

	public String getTargetHallID() {
		return targetHallID;
	}

}
