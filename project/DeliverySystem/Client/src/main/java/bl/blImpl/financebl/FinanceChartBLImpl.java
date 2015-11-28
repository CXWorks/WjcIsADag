package bl.blImpl.financebl;

import bl.blService.financeblService.FinanceChartBLService;
import bl.clientNetCache.CacheHelper;
import vo.financevo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import po.financedata.PaymentPO;
import rmi.financedata.PaymentDataService;

/**
 * Created by Sissel on 2015/10/26.
 */
public class FinanceChartBLImpl implements FinanceChartBLService {
	private PaymentDataService paymentDataService;
	public FinanceChartBLImpl(){
		this.paymentDataService=CacheHelper.getPaymentDataService();
	}

	/* (non-Javadoc)
	 * @see bl.blService.financeblService.FinanceChartBLService#getCompanyState()
	 */
	public CalculateVO getCompanyState() {
		// TODO Auto-generated method stub
		return new CalculateVO();
	}
	
	private boolean comp(Calendar strat,Calendar end,Calendar target){
		if(target.after(strat)&&target.before(end))
			return true;
		return false;
	}

    @Override
    public BaseChartVO getBarChart(Calendar begin, Calendar end, FinanceBaseChartType type) {
        return new BaseChartVO();
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
        return new BaseChartVO();
    }

    @Override
    public CalculateVO getCompanyState(Calendar begin, Calendar end) {
        return new CalculateVO();
    }
}
