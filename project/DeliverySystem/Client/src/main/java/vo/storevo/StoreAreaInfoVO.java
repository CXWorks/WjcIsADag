package vo.storevo;

import model.store.StoreArea;
import model.store.StoreAreaCode;

/** 
 * Client//vo.storevo//StoreAreaInfoVO.java
 * @author CXWorks
 * @date 2015年12月2日 下午7:28:44
 * @version 1.0 
 */
public class StoreAreaInfoVO {
	public StoreAreaCode area;
	public int totalRows;
	public int totalShelves;
	public double usedProporttion;
	/**
	 * @param area
	 * @param totalRows
	 * @param totalShelves
	 * @param usedProporttion
	 */
	public StoreAreaInfoVO(StoreArea mother) {
		super();
		this.area = mother.getAreaID();
		this.totalRows = mother.getRowNumber();
		this.totalShelves = mother.getShelfNumber();
		this.usedProporttion = mother.getUsedProportion();
	}
	
}
