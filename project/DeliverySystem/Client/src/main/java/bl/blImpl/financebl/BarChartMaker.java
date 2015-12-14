package bl.blImpl.financebl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Stream;

import po.financedata.FinancePayEnum;
import po.financedata.PaymentPO;
import po.financedata.RevenuePO;
import vo.financevo.BaseChartVO;

/** 
 * Client//bl.blImpl.financebl//BarChartMaker.java
 * @author CXWorks
 * @date 2015年12月7日 上午9:41:56
 * @version 1.0 
 */
public class BarChartMaker {
	private int getMonthNum(Calendar start,Calendar end){
		int year1=start.get(Calendar.YEAR);
		int month1=start.get(Calendar.MONTH);
		int year2=end.get(Calendar.YEAR);
		int month2=end.get(Calendar.MONTH);
		return (year2-year1)*12+month2-month1;
	}
	private boolean comp(Calendar strat,Calendar end,Calendar target){
		if(target.after(strat)&&target.before(end))
			return true;
		return false;
	}
	public BaseChartVO make_MonthIO(Calendar start,Calendar end,ArrayList<PaymentPO> paymentPOs,ArrayList<RevenuePO> revenuePOs){
		if (start==null||end==null) {
			if (start==null) {
				Calendar startP = Calendar.getInstance(),startR=Calendar.getInstance();
				
				if (!paymentPOs.isEmpty()) {
					startP=paymentPOs.stream()
							.min(Comparator.comparing((PaymentPO pay)->pay.getDate())).get().getDate();
				}
				if (!revenuePOs.isEmpty()) {
					startR=revenuePOs.stream()
							.min(Comparator.comparing((RevenuePO re)->re.getDate())).get().getDate();
				}
				start=(startP.before(startR))?startP:startR;
			}
			if (end==null) {
				end=Calendar.getInstance();
			}
		}
		//
		BaseChartVO baseChartVO=new BaseChartVO();
		baseChartVO.title = "每月收支比较图";
        baseChartVO.mainXType = "月份/种类";
        baseChartVO.mainYType = "数量";
		int monthNum=this.getMonthNum(start, end);
		//
		baseChartVO.categories=new String[monthNum+1];
		for (int i = 0; i <= monthNum; i++) {
			int temp=(i+start.get(Calendar.MONTH))%12;
			if (temp==0) {
				temp=12;
			}
			baseChartVO.categories[i]=Integer.toString(temp);
		}
		baseChartVO.elements=new String[2];
		baseChartVO.elements[0]="收入";
		baseChartVO.elements[1]="支出";
		//
		baseChartVO.values=new double[monthNum+1][2];
		double[][] use=baseChartVO.values;
		for (PaymentPO paymentPO : paymentPOs) {
			if (comp(start, end, paymentPO.getDate())) {
				int index=this.getMonthNum(start, paymentPO.getDate());
				use[index][1]+=Double.parseDouble(paymentPO.getAmount());
			}
		}
		//
		for (RevenuePO revenuePO : revenuePOs) {
			if (comp(start, end, revenuePO.getDate())) {
				int index=this.getMonthNum(start, revenuePO.getDate());
				use[index][0]+=Double.parseDouble(revenuePO.getAmount());
			}
		}
		
		//
		
		
		
		return baseChartVO;
	}
}
