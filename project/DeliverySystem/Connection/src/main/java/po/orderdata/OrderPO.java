package po.orderdata;

import java.io.Serializable;
import java.util.ArrayList;

import po.FormEnum;
import po.FormPO;
import po.receivedata.StateEnum;

public class OrderPO extends FormPO implements Serializable {
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
	private String money;
	private String goodsType;
	private DeliverTypeEnum type;
	private PackingEnum pack;
	private ArrayList<String> FormIDs;
	private String targetHallID;
	
	public OrderPO(String formID,String nameFrom, String nameTo, String unitFrom,
			String unitTo,  String addressFrom,String addressTo,String phoneNumFrom, String phoneNumTo,
			String telNumFrom, String telNumTo, String goodsNum,
			String goodsName, String weight, String volume,String money, String goodsType,
			String type, String pack,ArrayList<String> formIDs, String targetHallID) {
		super(FormEnum.ORDER,formID);
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
		this.setType(type);
		this.setPack(pack);
		FormIDs = formIDs;
		this.targetHallID = targetHallID;
		this.formType = FormEnum.ORDER;
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

	public String getAddressFrom(){
		return addressFrom;
	}
	
	public String getAddressTo(){
		return addressTo;
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

	public String getGoodsType() {
		return goodsType;
	}

	public DeliverTypeEnum getType() {
		return type;
	}
	
	public PackingEnum getPack(){
		return pack;
	}
	
	
	public String getMoney() {
		return money;
	}

	public void setType(String type) {
		switch (type) {
		case "SLOW":
			this.type = DeliverTypeEnum.SLOW;
			break;
		case "NORMAL":
			this.type = DeliverTypeEnum.NORMAL;
			break;
		case "FAST":
			this.type = DeliverTypeEnum.FAST;
			break;
		}
	}
	
	public void setPack(String pack) {
		switch (pack) {
		case "PAPER":
			this.pack=PackingEnum.PAPER;
			break;
		case "WOOD":
			this.pack=PackingEnum.WOOD;
			break;
		case "BAG":
			this.pack=PackingEnum.BAG;
			break;
		case "OTHER":
			this.pack=PackingEnum.OTHER;
			break;
		}
	}
	
	public ArrayList<String> getFormIDs() {
		return FormIDs;
	}

	public String getTargetHallID() {
		return targetHallID;
	}

}
