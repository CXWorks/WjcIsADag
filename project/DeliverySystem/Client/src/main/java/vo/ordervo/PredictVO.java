package vo.ordervo;

public class PredictVO {
	private String expense;
	private String predictDate;
	//
	public PredictVO(String expense,String date){
		this.expense=expense;
		this.predictDate=date;
	}
	public String getExpense() {
		return expense;
	}
	public String getPredictDate() {
		return predictDate;
	}
	
}
