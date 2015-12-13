package operation;

import java.io.Serializable;

import po.CommonPO;
import po.FormEnum;
import po.InfoEnum;

import com.sun.prism.PixelFormat.DataType;

/** 
 * Connection//operation//Operation.java
 * @author CXWorks
 * @date 2015年12月13日 上午9:06:36
 * @version 1.0 
 */
public class Operation implements Serializable {
	public DataType dataType;
	public FormEnum formEnum;
	public InfoEnum infoEnum;
	public OperationTypeEnum operationTypeEnum;
	
	public String formID;// if have
	public CommonPO src;
	//
	public String operatorID;//
	//
	public long version;
}
