package po.orderdata;

import java.io.Serializable;

import po.CommonPO;
import po.FormPO;
import po.InfoEnum;
import po.InfoPO;

public class PredictPO extends InfoPO implements Serializable{
	
	private String expense;
	private String predictDate;
	
	public String getExpense() {
		return expense;
	}
	public String getPredictDate() {
		return predictDate;
	}
	public void setExpense(String expense) {
		this.expense = expense;
	}
	public void setPredictDate(String predictDate) {
		this.predictDate = predictDate;
	}
	
	public PredictPO(){
		super(InfoEnum.PREDICT);
	}
}
