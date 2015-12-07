package bl.blImpl.financebl;

import bl.blService.financeblService.FinanceChartBLService;
import bl.clientNetCache.CacheHelper;
import vo.financevo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import po.financedata.PaymentPO;
import po.financedata.RevenuePO;
import rmi.financedata.PaymentDataService;
import rmi.financedata.RevenueDataService;

/**
 * Created by Sissel on 2015/10/26.
 */
public class FinanceChartBLImpl implements FinanceChartBLService {
	private PaymentDataService paymentDataService;
	private RevenueDataService revenueDataService;
	private BarChartMaker barChartMaker;
	public FinanceChartBLImpl(){
		this.paymentDataService=CacheHelper.getPaymentDataService();
		revenueDataService=CacheHelper.getRevenueDataService();
		barChartMaker=new BarChartMaker();
	}

	/* (non-Javadoc)
	 * @see bl.blService.financeblService.FinanceChartBLService#getCompanyState()
	 */
	public CalculateVO getCompanyState() {
		try {
			ArrayList<PaymentPO> paymentPOs=paymentDataService.getAll();
			ArrayList<RevenuePO> revenuePOs=revenueDataService.getAll();
			double income=0;
			double outcome=0;
			for (RevenuePO revenuePO : revenuePOs) {
				income+=Double.parseDouble(revenuePO.getAmount());
			}
			for (PaymentPO paymentPO : paymentPOs) {
				outcome+=Double.parseDouble(paymentPO.getAmount());
			}
			return new CalculateVO(income,outcome,income-outcome);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private boolean comp(Calendar strat,Calendar end,Calendar target){
		if(target.after(strat)&&target.before(end))
			return true;
		return false;
	}

    @Override
    public BaseChartVO getBarChart(Calendar begin, Calendar end, FinanceBaseChartType type) {
    	//TODO wait UI
        try {
			ArrayList<PaymentPO> paymentPOs=paymentDataService.getByTime(begin, end);
			ArrayList<RevenuePO> revenuePOs=revenueDataService.getByTime(begin, end);
			switch (type) {
			case IO_MONTH:
				return barChartMaker.make_MonthIO(begin, end, paymentPOs, revenuePOs);

			default:
				return null;
			}
		} catch (RemoteException e) {
			BaseChartVO vo = new BaseChartVO();

	        vo.title = "每月花费时间比较图";
	        vo.mainXType = "月份/种类";
	        vo.mainYType = "时数";

	        vo.categories = new String[]{"一月", "二月", "三月"};
	        vo.elements = new String[]{"吃饭", "睡觉", "打豆豆"};

	        vo.values = new double[][]{{23,45,12}, {1,1,23}, {12,12,12}};

	        return vo;
		}
    }

    @Override
    public PieChartVO getPieChart(Calendar begin, Calendar end, FinancePieChartType type) {
        
        try {
        	PieChartVO vo=new PieChartVO();
        	vo.initial();
			ArrayList<PaymentPO> src=paymentDataService.getAll();
			for (PaymentPO paymentPO : src) {
				if (comp(begin, end, paymentPO.getDate())) {
					double amount=Double.parseDouble(paymentPO.getAmount());
					vo.addOriginMapByType(paymentPO.getItem(), amount);
				}
			}
			//
			return vo;
		} catch (RemoteException|NullPointerException e) {
			PieChartVO vo =  new PieChartVO();
	        vo.title = "支出类型比例饼状图";
	        vo.originMap = new HashMap<>();
	        vo.originMap.put("抽烟", 233.0);
	        vo.originMap.put("喝酒", 233.0);
	        vo.originMap.put("玩舰娘", 450.0);
	        return vo;
		}
        //
    }

    @Override
    public BaseChartVO getLineChart(Calendar begin, Calendar end, FinanceBaseChartType type) {
    	try {
			ArrayList<PaymentPO> paymentPOs=paymentDataService.getByTime(begin, end);
			ArrayList<RevenuePO> revenuePOs=revenueDataService.getByTime(begin, end);
			switch (type) {
			case IO_MONTH:
				return barChartMaker.make_MonthIO(begin, end, paymentPOs, revenuePOs);

			default:
				return null;
			}
		} catch (RemoteException e) {
			BaseChartVO vo = new BaseChartVO();

	        vo.title = "每月花费时间比较图";
	        vo.mainXType = "月份/种类";
	        vo.mainYType = "时数";

	        vo.categories = new String[]{"一月", "二月", "三月"};
	        vo.elements = new String[]{"吃饭", "睡觉", "打豆豆"};

	        vo.values = new double[][]{{23,45,12}, {1,1,23}, {12,12,12}};

	        return vo;
		}
    }

    @Override
    public CalculateVO getCompanyState(Calendar begin, Calendar end) {
        //TODO waitting 
    	return null;
    }
}
