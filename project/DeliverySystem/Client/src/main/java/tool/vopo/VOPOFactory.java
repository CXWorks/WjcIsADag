package tool.vopo;

import po.FormPO;
import util.DataType;
import vo.CommonVO;
import vo.FormVO;
import vo.InfoVO;
import vo.accountvo.AccountVO;
import vo.configurationvo.City2DVO;
import vo.configurationvo.CityDistanceVO;
import vo.configurationvo.PackVO;
import vo.configurationvo.PriceVO;
import vo.configurationvo.ProportionVO;
import vo.configurationvo.SalaryStrategyVO;
import vo.delivervo.DeliverVO;
import vo.financevo.BankAccountVO;
import vo.financevo.PaymentVO;
import vo.financevo.RevenueVO;
import vo.initialdata.InitialDataVO;
import vo.managevo.car.CarVO;
import vo.managevo.institution.CenterVO;
import vo.managevo.institution.HallVO;
import vo.managevo.staff.DriverVO;
import vo.managevo.staff.StaffVO;
import vo.ordervo.OrderVO;
import vo.receivevo.ReceiveVO;
import vo.storevo.StoreInVO;
import vo.storevo.StoreOutVO;
import vo.systemvo.LogVO;
import vo.transitvo.CenterOutVO;
import vo.transitvo.LoadVO;
import po.*;
import po.accountdata.AccountPO;
import po.companydata.CarPO;
import po.companydata.CenterPO;
import po.companydata.HallPO;
import po.configurationdata.City2DPO;
import po.configurationdata.CityDistancePO;
import po.configurationdata.PackPO;
import po.configurationdata.PricePO;
import po.configurationdata.ProportionPO;
import po.configurationdata.SalaryStrategyPO;
import po.deliverdata.DeliverPO;
import po.financedata.BankAccountPO;
import po.financedata.PaymentPO;
import po.financedata.RevenuePO;
import po.initialdata.InitialDataPO;
import po.memberdata.DriverPO;
import po.memberdata.StaffPO;
import po.orderdata.OrderPO;
import po.receivedata.ReceivePO;
import po.storedata.StoreInPO;
import po.storedata.StoreOutPO;
import po.systemdata.LogPO;
import po.transportdata.CenterOutPO;
import po.transportdata.LoadPO;

import java.util.LinkedList;
import java.util.List;

/**
 * Client//tool.vopo//VOPOFactory.java
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
	public CommonVO transPOtoVO(CommonPO po) {
		if (po.getDataType()==DataType.DATA) {
			InfoPO info=(InfoPO)po;
			switch (info.getInfoEnum()) {
			case DRIVER:
				return new DriverVO((DriverPO)info);
			case ACCOUNT:
				return new AccountVO((AccountPO)info);
			case CAR:
				return new CarVO((CarPO)info);
			case CENTER:
				return new CenterVO((CenterPO)info);
			case HALL:
				return new HallVO((HallPO)info);
			case CITY_2D:
				return new City2DVO((City2DPO)info);
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

			case LOG:
				return new LogVO((LogPO)info);
			case INITIAL_DATA:
				return new InitialDataVO((InitialDataPO)info);
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

	public CommonPO transVOtoPO(CommonVO vo) {
		if (vo.getDataType()==DataType.DATA) {
			InfoVO info=(InfoVO)vo;
			switch (info.getInfoEnum()) {
			case ACCOUNT:
				return ((AccountVO)vo).toPO();
			case CAR:
				return ((CarVO)vo).toPO();
			case CENTER:
				return ((CenterVO)vo).toPO();
			case HALL:
				return ((HallVO)vo).toPO();
			case CITY_2D:
				return((City2DVO)vo).toPO();
			case PACK:
				return ((PackVO)vo).toPO();
			case PRICE:
				return ((PriceVO)vo).toPO();
			case PROPORTION:
				return ((ProportionVO)vo).toPO();
			case SALARY:
				return ((SalaryStrategyVO)vo).toPO();
			case BANK_ACCOUNT:
				return ((BankAccountVO)vo).toPO();
			case STAFF:
				return ((StaffVO)vo).toPO();
			case DRIVER:
				return ((DriverVO)vo).toPO();
			case LOG:
				return ((LogVO)vo).toPO();
			case INITIAL_DATA:
				return ((InitialDataVO)vo).toPO();
			default:
				return null;
			}
		} else {
			FormVO form=(FormVO)vo;
			switch (form.getFormType()) {
			case ORDER:
				return ((OrderVO)vo).toPO();
			case DELIVER:
				return ((DeliverVO)vo).toPO();
			case PAYMENT:
				return ((PaymentVO)vo).toPO();
			case REVENUE:
				return ((RevenueVO)vo).toPO();
			case RECEIVE:
				return ((ReceiveVO)vo).toPO();
			case TRANSPORT_CENTER:
				return ((CenterOutVO)vo).toPO();
			case TRANSPORT_HALL:
				return ((LoadVO)vo).toPO();
			case STORE_IN:
				return ((StoreInVO)vo).toPO();
			case STORE_OUT:
				return ((StoreOutVO)vo).toPO();
			default:
				return null;
			}
		}
	}

	public List<? extends CommonVO> transPOtoVO(List<? extends CommonPO> pos){
		List<CommonVO> vos = new LinkedList<>();
		for (CommonPO po : pos) {
			vos.add(transPOtoVO(po));
		}
		return vos;
	}

	public List<? extends CommonPO> transVOtoPO(List<? extends CommonVO> vos){
		List<CommonPO> pos = new LinkedList<>();
		for (CommonVO vo : vos) {
			pos.add(transVOtoPO(vo));
		}
		return pos;
	}

}
