package operation;

import java.io.Serializable;

import po.CommonPO;
import po.FormEnum;
import po.InfoEnum;
import po.InfoPO;
import po.FormPO;
import util.DataType;

/** 
 * Connection//operation//Operation.java
 * @author CXWorks
 * @date 2015年12月13日 上午9:06:36
 * @version 1.0 
 */
public class Operation implements Serializable {
	public DataType dataType;//must
	public FormEnum formEnum;//if
	public InfoEnum infoEnum;//if
	public OperationTypeEnum operationTypeEnum;//must
	
	public String formID;// if have
	public CommonPO src;//must
	//
	public String operatorID;//must
	//
	public long version;
	private Operation(){}
	//
	public static Operation build(OperationTypeEnum operationTypeEnum,CommonPO commonPO){
		Operation operation=new Operation();
		//
		operation.operationTypeEnum=operationTypeEnum;
		operation.src=commonPO;
		operation.operatorID=commonPO.getCache_OperatorID();
		operation.dataType=commonPO.getDataType();
		if(operation.dataType.equals(DataType.DATA)){
			operation.infoEnum=((InfoPO)commonPO).getInfoEnum();
		}
		else {
			operation.formEnum=((FormPO)commonPO).getFormType();
			operation.formID=((FormPO)commonPO).getFormID();
		}
		
		//
		return operation;
	}
}
