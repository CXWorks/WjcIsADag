package model.store;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreModel implements Serializable {

	String centerID;

	private StoreArea air;
	private StoreArea rail;
	private StoreArea road;
	private StoreArea flex;

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

	public StoreArea getAir() {
		return air;
	}

	public StoreArea getRail() {
		return rail;
	}

	public StoreArea getRoad() {
		return road;
	}

	public StoreArea getFlex() {
		return flex;
	}

}
