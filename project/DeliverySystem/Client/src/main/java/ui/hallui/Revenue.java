package ui.hallui;

public class Revenue {
	private String order;
	private String money;
	
	
    Revenue(String order,String money){
    	this.money=money;
    	this.order=order;
    	
    }
	public String getOrder() {
		return order;
	}


	public String getMoney() {
		return money;
	}


	public void setOrder(String order) {
		this.order = order;
	}


	public void setMoney(String money) {
		this.money = money;
	}
}
