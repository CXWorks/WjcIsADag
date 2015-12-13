package bl.blImpl.financebl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import po.financedata.FinancePayEnum;
import po.financedata.PaymentPO;
import po.financedata.RevenuePO;
import vo.financevo.PieChartVO;

/** 
 * Client//bl.blImpl.financebl//PieChartMaker.java
 * @author CXWorks
 * @date 2015年12月11日 下午8:18:49
 * @version 1.0 
 */
public class PieChartMaker {
	private boolean comp(Calendar src,Calendar tar){
		return src.get(Calendar.YEAR)<=tar.get(Calendar.YEAR)&&src.get(Calendar.MONTH)<=tar.get(Calendar.MONTH);
	}
	
	public PieChartVO monthPay(ArrayList<PaymentPO> paymentPO){
		Calendar begin=paymentPO.get(0).getDate();
		Calendar end=paymentPO.get(paymentPO.size()-1).getDate();
		PieChartVO pieChartVO=new PieChartVO();
		pieChartVO.title="各个月支出统计";
		pieChartVO.valueType="月份";
		//
		int beYear=begin.get(Calendar.YEAR);
		int beMonth=begin.get(Calendar.MONTH)+1;
		int enYear=end.get(Calendar.YEAR);
		int enMonth=end.get(Calendar.MONTH)+1;
		//
		int total=(enYear-beYear)*12+enMonth-beMonth;
		//
		if (total==0) {
			pieChartVO.originMap=new HashMap<String, Double>(1);
			double all=0.0;
			for (PaymentPO paymentPO2 : paymentPO) {
				all+=Double.parseDouble(paymentPO2.getAmount());
			}
			pieChartVO.originMap.put(beYear+"年"+beMonth+"月", 1.0);
			return pieChartVO;
		}
		//
		pieChartVO.originMap=new HashMap<String, Double>(total);
		int nowYear=beYear;
		int nowMonth=beMonth;
		double io=0.0;
		int index=0;
		Calendar use=Calendar.getInstance();
		for (int i = 0; i < total; i++) {
			use.set(nowYear, nowMonth, 0);
			for (;index<paymentPO.size(); index++) {
				PaymentPO temp=paymentPO.get(index);
				if (this.comp(temp.getDate(), use)) {
					io+=Double.parseDouble(temp.getAmount());
				}
			}
			//
			pieChartVO.originMap.put(nowYear+"年"+nowMonth+"月", io);
			nowMonth++;
			io=0.0;
			if (nowMonth>=13) {
				nowMonth=1;
				nowYear++;
			}
		}
		
		return pieChartVO;
		
	}
	
	public PieChartVO monthRevenue(ArrayList<RevenuePO> revenuePO){
		if (revenuePO.isEmpty()) {
			PieChartVO pieChartVO=new PieChartVO();
			pieChartVO.title="各个月收入统计";
			pieChartVO.valueType="月份";
			pieChartVO.originMap=new HashMap<String, Double>();
			return pieChartVO;
		}
		Calendar begin=revenuePO.get(0).getDate();
		Calendar end=revenuePO.get(revenuePO.size()-1).getDate();
		PieChartVO pieChartVO=new PieChartVO();
		pieChartVO.title="各个月收入统计";
		pieChartVO.valueType="月份";
		//
		int beYear=begin.get(Calendar.YEAR);
		int beMonth=begin.get(Calendar.MONTH);
		int enYear=end.get(Calendar.YEAR);
		int enMonth=end.get(Calendar.MONTH);
		//
		int total=(enYear-beYear)*12+enMonth-beMonth;
		//
		if (total==0) {
			pieChartVO.originMap=new HashMap<String, Double>(1);
			double all=0.0;
			for (RevenuePO revenuePO2 : revenuePO) {
				all+=Double.parseDouble(revenuePO2.getAmount());
			}
			pieChartVO.originMap.put(beYear+"年"+beMonth+"月", all);
			return pieChartVO;
		}
		//
		pieChartVO.originMap=new HashMap<String, Double>(total);
		int nowYear=beYear;
		int nowMonth=beMonth;
		double io=0.0;
		int index=0;
		Calendar use=Calendar.getInstance();
		for (int i = 0; i < total; i++) {
			use.set(nowYear, nowMonth, 0);
			for (;index<revenuePO.size(); index++) {
				RevenuePO temp=revenuePO.get(index);
				if (this.comp(temp.getDate(), use)) {
					io+=Double.parseDouble(temp.getAmount());
				}
			}
			//
			pieChartVO.originMap.put(nowYear+"年"+nowMonth+"月", io);
			nowMonth++;
			io=0.0;
			if (nowMonth>=13) {
				nowMonth=1;
				nowYear++;
			}
		}
		
		return pieChartVO;
	}

	public PieChartVO typePay(ArrayList<PaymentPO> paymentPO){
		PieChartVO pieChartVO=new PieChartVO();
		pieChartVO.title="各类型支出统计图";
		pieChartVO.valueType="支出类型";
		//
		pieChartVO.originMap=new HashMap<String, Double>(4);
		Map<String, Double> map=pieChartVO.originMap;
		map.put(FinancePayEnum.AWARD.getChinese(), 0.0);
		map.put(FinancePayEnum.RENT.getChinese(), 0.0);
		map.put(FinancePayEnum.SALARY.getChinese(), 0.0);
		map.put(FinancePayEnum.TRANSPORTION.getChinese(), 0.0);
		for (PaymentPO paymentPO2 : paymentPO) {
			double te=map.get(paymentPO2.getItem().getChinese());
			map.replace(paymentPO2.getItem().getChinese(), te+Double.parseDouble(paymentPO2.getAmount()));
		}
		
		return pieChartVO;
	}
	
}
