package bl.tool.vopo;

import po.FormPO;
import util.DataType;
import vo.CommonVO;
import vo.FormVO;
import vo.accountvo.AccountVO;
import vo.configurationvo.CityDistanceVO;
import vo.configurationvo.PackVO;
import vo.configurationvo.PriceVO;
import vo.configurationvo.ProportionVO;
import vo.configurationvo.SalaryStrategyVO;
import vo.delivervo.DeliverVO;
import vo.financevo.BankAccountVO;
import vo.financevo.PaymentVO;
import vo.financevo.RevenueVO;
import vo.managevo.car.CarVO;
import vo.managevo.institution.CenterVO;
import vo.managevo.institution.HallVO;
import vo.managevo.staff.StaffVO;
import vo.ordervo.OrderVO;
import vo.receivevo.ReceiveVO;
import vo.storevo.StoreInVO;
import vo.storevo.StoreOutVO;
import vo.transitvo.CenterOutVO;
import vo.transitvo.LoadVO;
import po.*;
import po.accountdata.AccountPO;
import po.companydata.CarPO;
import po.companydata.CenterPO;
import po.companydata.HallPO;
import po.configurationdata.CityDistancePO;
import po.configurationdata.PackPO;
import po.configurationdata.PricePO;
import po.configurationdata.ProportionPO;
import po.configurationdata.SalaryStrategyPO;
import po.deliverdata.DeliverPO;
import po.financedata.BankAccountPO;
import po.financedata.PaymentPO;
import po.financedata.RevenuePO;
import po.memberdata.StaffPO;
import po.orderdata.OrderPO;
import po.receivedata.ReceivePO;
import po.storedata.StoreInPO;
import po.storedata.StoreOutPO;
import po.transportdata.CenterOutPO;
import po.transportdata.LoadPO;
/** 
 * Client//bl.tool.vopo//VOPOFactory.java
 * @author CXWorks
 * @date 2015年11月18日 下午2:19:31
 * @version 1.0 
 */
public class VOPOFactory {
	/**
	 * 根据po来创建vo，深拷贝
	 * @param po
	 * @return
	 * 实现的不是很完美，，因为没有想到更好的方法
	 */
	public static CommonVO transPOtoVO(CommonPO po) {
		if (po.getDataType()==DataType.DATA) {
			InfoPO info=(InfoPO)po;
			switch (info.getInfoEnum()) {
			case ACCOUNT:
				return new AccountVO((AccountPO)info);
			case CAR:
				return new CarVO((CarPO)info);
			case CENTER:
				return new CenterVO((CenterPO)info);
			case HALL:
				return new HallVO((HallPO)info);
			case CITY_DISTANCE:
				return new CityDistanceVO((CityDistancePO)info);
			case PACK:
				return new PackVO((PackPO)info);
			case PRICE:
				return new PriceVO((PricePO)info);
			case PROPORTION:
				return new ProportionVO((ProportionPO)info);
			case SALARY:
				return new SalaryStrategyVO((SalaryStrategyPO)info);
			case BANK_ACCOUNT:
				return new BankAccountVO((BankAccountPO)info);
			case STAFF:
				return new StaffVO((StaffPO)info);
			default:
				return null;
			}
		} else {
			FormPO form=(FormPO)po;
			switch (form.getFormType()) {
			case ORDER:
				return new OrderVO((OrderPO)form);
			case DELIVER:
				return new DeliverVO((DeliverPO)form);
			case PAYMENT:
				return new PaymentVO((PaymentPO)form);
			case REVENUE:
				return new RevenueVO((RevenuePO)form);
			case RECEIVE:
				return new ReceiveVO((ReceivePO)form);
			case TRANSPORT_CENTER:
				return new CenterOutVO((CenterOutPO)form);
			case TRANSPORT_HALL:
				return new LoadVO((LoadPO)form);
			case STORE_IN:
				return new StoreInVO((StoreInPO)form);
			case STORE_OUT:
				return new StoreOutVO((StoreOutPO)form);
			default:
				return null;
			}
		}
		
	}

	public static CommonPO transVOtoPO(CommonVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
