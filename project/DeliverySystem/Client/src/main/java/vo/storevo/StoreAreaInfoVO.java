package vo.storevo;

import model.store.StoreArea;
import model.store.StoreAreaCode;
import util.DataType;
import vo.CommonVO;

/**
 * Client//vo.storevo//StoreAreaInfoVO.java
 * @author CXWorks
 * @date 2015年12月2日 下午7:28:44
 * @version 1.0
 */
public class StoreAreaInfoVO extends CommonVO{
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
		super(DataType.DATA);
		this.area = mother.getAreaID();
		this.totalRows = mother.getRowNumber();
		this.totalShelves = mother.getShelfNumber();
		this.usedProporttion = mother.getUsedProportion();
	}
	public StoreAreaCode getArea() {
		return area;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public int getTotalShelves() {
		return totalShelves;
	}
	public double getUsedProporttion() {
		return usedProporttion;
	}



}
