package model.store;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreModel implements Serializable {
	
	private StoreArea air;
	private StoreArea rail;
	private StoreArea road;
	private StoreArea flex;
	
	public StoreModel(StoreArea air, StoreArea rail, StoreArea road,
			StoreArea flex) {
		super();
		this.air = air;
		this.rail = rail;
		this.road = road;
		this.flex = flex;
	}

	
}
