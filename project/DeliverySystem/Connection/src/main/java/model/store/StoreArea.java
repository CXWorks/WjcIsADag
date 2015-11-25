package model.store;

import java.util.List;


public class StoreArea {
	List<StoreRow> rows;
	StoreAreaCode areaID;

	public StoreArea(List<StoreRow> rows, StoreAreaCode areaID) {
		super();
		this.rows = rows;
		this.areaID = areaID;
	}

	public List<StoreRow> getRows() {
		return rows;
	}

	public StoreAreaCode getAreaID() {
		return areaID;
	}
}
