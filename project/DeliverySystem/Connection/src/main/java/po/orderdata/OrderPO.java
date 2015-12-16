package po.orderdata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

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
	private String receivePeople;
	private Calendar receiveDate;
	
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
	public void finfished(Calendar receiveCalendar,String signPeople){
		this.receiveDate=receiveCalendar;
		this.receivePeople=signPeople;
	}

	//
	public String getNameFrom() {
		return nameFrom;
	}

	/**
	 * @param formEnum
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
	 * @param money
	 * @param goodsType
	 * @param type
	 * @param pack
	 * @param formIDs
	 * @param targetHallID
	 * @param receivePeople
	 * @param receiveDate
	 */
	public OrderPO(String formID, String nameFrom,
			String nameTo, String addressFrom, String addressTo,
			String unitFrom, String unitTo, String phoneNumFrom,
			String phoneNumTo, String telNumFrom, String telNumTo,
			String goodsNum, String goodsName, String weight, String volume,
			String money, String goodsType, String type,
			String pack, ArrayList<String> formIDs, String targetHallID,
			String receivePeople, Calendar receiveDate,String createrID) {
		super(FormEnum.ORDER, formID,createrID);
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
		this.setType(type);
		this.setPack(pack);
		FormIDs = formIDs;
		this.targetHallID = targetHallID;
		this.receivePeople = receivePeople;
		this.receiveDate = receiveDate;
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
	public Calendar getOrderDate(){
		Calendar ans=Calendar.getInstance();
		int m=Integer.parseInt(formID.substring(0, 2));
		int d=Integer.parseInt(formID.substring(2, 4));
		ans.set(Calendar.MONTH, m);
		ans.set(Calendar.DAY_OF_MONTH, d);
		return ans;
	}

}
