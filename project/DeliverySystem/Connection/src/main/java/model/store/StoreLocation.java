package model.store;

import java.io.Serializable;

import po.receivedata.StateEnum;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreLocation implements Serializable,Cloneable {
	private StoreAreaCode area;
	private int row;
	private int shelf;
	private int position;
	private String orderID;
	
	public StoreLocation (StoreAreaCode area,int row,int shelf,int position){
		super();
		this.area=area;
		this.row=row;
		this.shelf=shelf;
		this.position=position;
	}

	public StoreLocation(StoreAreaCode area, int row, int shelf, int position,
			String orderID) {
		super();
		this.area = area;
		this.row = row;
		this.shelf = shelf;
		this.position = position;
		this.orderID = orderID;
	}

	public StoreLocation(String message) {
		String[] list = message.split("-");
		this.setArea(list[0]);
		this.row = Integer.parseInt(list[1]);
		this.shelf = Integer.parseInt(list[2]);
		this.position = Integer.parseInt(list[3]);
		this.orderID = list[4];
	}

	public String getLocationForSQL() {
		return area.toString() + "-" + row + "-" + shelf + "-" + position + "-"
				+ orderID;
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

	public StoreAreaCode getArea() {
		return area;
	}

	public int getRow() {
		return row;
	}

	public int getShelf() {
		return shelf;
	}

	public int getPosition() {
		return position;
	}

	public String getOrderID() {
		return orderID;
	}

}
