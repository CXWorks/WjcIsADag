package model.store;

import java.io.Serializable;
import java.util.List;

import message.OperationMessage;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreModel implements Serializable {

	String centerID;

    private static final long serialVersionUID = 8369651544671002011L;

	private StoreArea air;
	private StoreArea rail;
	private StoreArea road;
	private StoreArea flex;
	
	public OperationMessage reducePartition(StoreAreaCode area, int shelfNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public OperationMessage expandPartition(StoreAreaCode area, int shelfNumber) {
		// TODO Auto-generated method stub
		return null;
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
