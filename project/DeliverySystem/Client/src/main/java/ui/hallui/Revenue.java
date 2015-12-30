package ui.hallui;

public class Revenue {
	private String order;
	private String money;
    private String deliver;

    public String getDeliver() {
        return deliver;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }
	
    Revenue(String order,String money, String deliver){
        this.deliver = deliver;
    	this.money = money;
    	this.order = order;
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
