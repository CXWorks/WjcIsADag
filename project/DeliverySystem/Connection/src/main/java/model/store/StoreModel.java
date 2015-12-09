package model.store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import message.OperationMessage;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreModel implements Serializable {

	String centerID;

	private StoreArea air;
	private StoreArea rail;
	private StoreArea road;
	private StoreArea flex;
	
	public OperationMessage reducePartition(StoreAreaCode area, int shelfNumber) {
		StoreArea src=this.getArea(area);
		int srcNum=src.getShelfNumber();
		if (srcNum<shelfNumber) {
			return new OperationMessage(false, "not enough size");
		}
		for (int i = 0; i < shelfNumber; i++) {
			src.deleteLastShelf();
			flex.addShelf();
		}
		return new OperationMessage();
	}
	
	public OperationMessage expandPartition(StoreAreaCode area, int shelfNumber) {
		StoreArea acc=this.getArea(area);
		int srcNum=flex.getShelfNumber();
		if (srcNum<shelfNumber) {
			return new OperationMessage(false, "not enough size");
		}
		for (int i = 0; i < shelfNumber; i++) {
			flex.deleteLastShelf();
			acc.addShelf();
		}
		return new OperationMessage() ;
	}
	
	public OperationMessage moveShelf(StoreAreaCode code_now, int row_now,
			int shelf_now, StoreAreaCode code, int row, int shelf){
		StoreArea old=this.getArea(code_now);
		ArrayList<StoreLocation> src=old.getByShelf(row_now, shelf_now);
		if (src.size()==0) {
			return new OperationMessage();
		}
		LinkedList<String> orderID=new LinkedList<String>();
		for (int i = 0; i < src.size(); i++) {
			orderID.add(src.get(i).getOrderID());
		}
		
		//hard coding
		ArrayList<StoreLocation> oldChange=old.getList();
		for (StoreLocation storeLocation : oldChange) {
			if (storeLocation.getPosition()==row_now&&storeLocation.getShelf()==shelf_now) {
				oldChange.remove(storeLocation);
			}
		}
		//
		
		return new OperationMessage();
	}
	

	public StoreModel(String centerID,StoreArea air, StoreArea rail, StoreArea road,
			StoreArea flex) {
		super();
		this.centerID = centerID;
		this.air = air;
		this.rail = rail;
		this.road = road;
		this.flex = flex;
	}

	public StoreArea getArea(StoreAreaCode code){
        switch (code){
            case AIR:
                return air;
            case RAIL:
                return rail;
            case ROAD:
                return road;
            case FLEX:
                return flex;
        }
        return null;
    }

	public String getCenterID() {
		return centerID;
	}

}
