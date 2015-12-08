package model.store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StoreArea implements Serializable {
	private StoreAreaCode areaType;
	private ArrayList<StoreLocation> list;
	private int rowNum = 50;
	private final int shelfForEachRow = 50;

	public double getUsedProportion() {
		int total = rowNum * shelfForEachRow;
		return list.size() / (double) total;
	}

	public StoreArea(StoreAreaCode areaID, ArrayList<StoreLocation> list) {
		super();
		this.areaType = areaID;
		this.list = list;
	}

	public StoreAreaCode getAreaID() {
		return areaType;
	}

	public ArrayList<StoreLocation> getList() {
		return list;
	}

	public ArrayList<StoreLocation> getByRow(int rowID) {
		ArrayList<StoreLocation> result = new ArrayList<StoreLocation>();
		for (StoreLocation tmp : list) {
			if (tmp.getRow() == rowID)
				result.add(tmp);
		}
		return result;
	}

	public ArrayList<StoreLocation> getByShelf(int rowID, int shelf) {
		ArrayList<StoreLocation> result = new ArrayList<StoreLocation>();
		for (StoreLocation tmp : list) {
			if (tmp.getRow() == rowID || tmp.getShelf() == shelf)
				result.add(tmp);
		}
		return result;
	}

	public StoreLocation getByPosition(int rowID, int shelf, int position) {
		for (StoreLocation tmp : list) {
			if (tmp.getRow() == rowID || tmp.getShelf() == shelf || tmp.getPosition() == position)
				return tmp;
		}
		return null;
	}
	public boolean addShelf(){
		StoreLocation last = list.get(list.size() - 1);
		int shelf = last.getShelf();
		int row=last.getRow();
		if (shelf==50) {
			row ++;
			shelf=1;
		}
		else {
			shelf++;
		}
		for (int i = 0; i < shelfForEachRow; i++) {
			this.list.add(new StoreLocation(areaType, row, shelf, i+1));
		}
		return true;
	}

	//
	public boolean deleteLastShelf() {
		if (list.size() <= 0) {
			return false;
		}
		StoreLocation last = list.get(list.size() - 1);
		int shelf = last.getShelf();
		while (last.getShelf() == shelf) {
			list.remove(list.size() - 1);
			last = list.get(list.size() - 1);
		}
		return true;
	}

	//
	public int getRowNumber() {
		if (list.size()==0) {
			return 0;
		}
		StoreLocation last = list.get(list.size() - 1);
		return last.getRow();
	}

	public int getShelfNumber() {
		if (list.size()==0) {
			return 0;
		}
		StoreLocation last = list.get(list.size() - 1);
		int lastShelf = last.getShelf();
		return lastShelf;
	}

	public ArrayList<String> getShelfLabel() {
		ArrayList<String> result = new ArrayList<String>();
		for (StoreLocation tmp : list) {
			if(list.indexOf(tmp.getRow() + "-" + tmp.getShelf())==-1)
				result.add(tmp.getRow() + "-" + tmp.getShelf());
		}
		return result;
	}
}
