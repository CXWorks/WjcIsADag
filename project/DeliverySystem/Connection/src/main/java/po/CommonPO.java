package po;

import java.io.Serializable;

import util.DataType;

/**
 * 所有PO类型的父类
 * 
 * @author wjc
 * @version 2014.10.31
 */
public abstract class CommonPO implements Serializable{
	protected DataType dataType;
	protected CommonPO(DataType dataType) {
		this.dataType=dataType;
	}
	public DataType getDataType() {
		return dataType;
	}
	
}
