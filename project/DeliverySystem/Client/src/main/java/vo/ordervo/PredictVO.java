package vo.ordervo;

import java.util.Calendar;

public class PredictVO {
	private String expense;
	private Calendar predictDate;
	//
	public PredictVO(String expense,Calendar date){
		this.expense=expense;
		this.predictDate=date;
	}
	public String getExpense() {
		return expense;
	}
	public Calendar getPredictDate() {
		return predictDate;
	}
	
}
