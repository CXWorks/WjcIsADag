package vo.financevo;

import po.InfoEnum;
import vo.InfoVO;

/** 
 * Client//vo.financevo//CalculateVO.java
 * @author CXWorks
 * @date 2015年11月15日 下午2:48:16
 * @version 1.0 
 * 显示公司当前收入、支出、毛利润
 */
public class CalculateVO extends InfoVO{
	//income
	public double companyRevenue;
	//outcome
	public double companyPayment;
	//毛利润
	public double companyProfit;
	private CalculateVO(){
		super(InfoEnum.CALCULATE);
	}
	/**
	 * @param infoEnum
	 * @param companyRevenue
	 * @param companyPayment
	 * @param companyProfit
	 */
	public CalculateVO(double companyRevenue,
			double companyPayment, double companyProfit) {
		this();
		this.companyRevenue = companyRevenue;
		this.companyPayment = companyPayment;
		this.companyProfit = companyProfit;
	}
	
}
