package model.store;

import java.util.List;

public class StoreRow {
	List<StoreShelf> shelves;
	int rowID;

	public List<StoreShelf> getShelves() {
		return shelves;
	}

	public int getRowID() {
		return rowID;
	}

}
