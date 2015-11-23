package vo;

import java.io.Serializable;

import util.DataType;

/** 
 * Client//vo//CommonVO.java
 * @author CXWorks
 * @date 2015年11月18日 下午3:09:58
 * @version 1.0 
 */
public	abstract class CommonVO implements Serializable {
	protected DataType dataType;
	public CommonVO(DataType dataType){
		this.dataType=dataType;
	}
	public DataType getDataType() {
		return dataType;
	}
	
}
