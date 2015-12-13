package vo.configurationvo;

import java.util.HashMap;
import java.util.Map;

import po.InfoEnum;
import po.configurationdata.ProportionPO;
import po.orderdata.DeliverTypeEnum;
import userinfo.UserInfo;

public class ProportionVO extends ConfigurationVO{
	private HashMap<DeliverTypeEnum,Integer> proportion;
	public ProportionVO(){
		super(InfoEnum.PROPORTION);
	}
	public void setByType(DeliverTypeEnum deliverTypeEnum,int newProportion){
		proportion.replace(deliverTypeEnum, newProportion);
	}
	public int getByType(DeliverTypeEnum type) {
		return proportion.get(type);
	}
	public ProportionVO(ProportionPO po){
		this();
		this.proportion=po.getClonedProportion();
	}
	//
	public ProportionPO toPO(){
		ProportionPO temp=new ProportionPO();
		temp.setProportion(proportion);
		temp.setCache_OperatorID(UserInfo.getUserID());
		return temp;
	}
}
