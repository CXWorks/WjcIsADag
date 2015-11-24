package model.store;

import java.io.Serializable;

import po.receivedata.StateEnum;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreLocation implements Serializable{
    public StoreAreaCode area;
    public int row;
    public int shelf;
    public int position;
    public String orderID;
    
    public StoreLocation(String message){
    	String[] list = message.split(" ");
    	this.setArea(list[0]);
    	this.row = Integer.parseInt(list[1]);
    	this.shelf = Integer.parseInt(list[2]);
    	this.position = Integer.parseInt(list[3]);
    	this.orderID = list[4];
    }

	public void setArea(String area) {
		switch (area) {
		case "AIR":
			this.area = StoreAreaCode.AIR;
			break;
		case "FLEX":
			this.area = StoreAreaCode.FLEX;
			break;
		case "RAIL":
			this.area = StoreAreaCode.RAIL;
			break;
		case "ROAD":
			this.area = StoreAreaCode.ROAD;
			break;
		}
	}
    
    
}
