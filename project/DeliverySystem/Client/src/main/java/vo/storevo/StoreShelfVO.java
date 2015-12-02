package vo.storevo;

import po.InfoEnum;
import vo.InfoVO;

/** 
 * Client//vo.storevo//StoreShelfVO.java
 * @author CXWorks
 * @date 2015年12月2日 下午7:14:07
 * @version 1.0 
 */
public class StoreShelfVO extends InfoVO {
	private StoreShelfVO(){
		super(InfoEnum.STORE_SHELF);
	}
	//
	public int rowID;
	public int shelfID;
	public double usedProportion;//0.0~1.0
	/**
	 * @param rowID
	 * @param shelfID
	 * @param usedProportion
	 */
	public StoreShelfVO(int rowID, int shelfID,
			double usedProportion) {
		this();
		this.rowID = rowID;
		this.shelfID = shelfID;
		this.usedProportion = usedProportion;
	}
	
}
