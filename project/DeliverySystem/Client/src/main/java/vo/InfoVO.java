package vo;

import po.InfoEnum;
import util.DataType;

/** 
 * Client//vo//InfoVO.java
 * @author CXWorks
 * @date 2015年11月18日 下午4:34:04
 * @version 1.0 
 */
public class InfoVO extends CommonVO {
	private InfoEnum infoEnum;

	public InfoEnum getInfoEnum() {
		return infoEnum;
	}
	protected InfoVO(InfoEnum infoEnum){
		super(DataType.DATA);
		this.infoEnum=infoEnum;
	}
}
