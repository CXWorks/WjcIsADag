package model.store;

import java.util.ArrayList;
import java.util.List;


public class StoreArea {
	private StoreAreaCode areaType;
	private ArrayList<StoreLocation> list;

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
	public ArrayList<StoreLocation> getByRow(int rowID){
		ArrayList<StoreLocation> result = new ArrayList<StoreLocation>();
		for(StoreLocation tmp:list){
			if(tmp.getRow()==rowID)
				result.add(tmp);
		}
		return result;
	}
	public ArrayList<StoreLocation> getByShelf(int rowID,int shelf){
		ArrayList<StoreLocation> result = new ArrayList<StoreLocation>();
		for(StoreLocation tmp:list){
			if(tmp.getRow()==rowID||tmp.getShelf()==shelf)
				result.add(tmp);
		}
		return result;
	}
	public StoreLocation getByPosition(int rowID,int shelf,int position){
		for(StoreLocation tmp:list){
			if(tmp.getRow()==rowID||tmp.getShelf()==shelf||tmp.getPosition()==position)
				return tmp;
		}
		return null;
	}
}
