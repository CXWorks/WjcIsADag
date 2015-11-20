package po;

import util.DataType;

/** 
 * Connection//po//InfoPO.java
 * @author CXWorks
 * @date 2015年11月18日 下午4:24:52
 * @version 1.0 
 */
public abstract class InfoPO extends CommonPO {
	private InfoEnum infoEnum;
	protected InfoPO(InfoEnum infoEnum){
		super(DataType.DATA);
		this.infoEnum=infoEnum;
	}
	public InfoEnum getInfoEnum() {
		return infoEnum;
	}
	
}
