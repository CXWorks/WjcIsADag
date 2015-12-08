package bl.blImpl.storebl;

import message.OperationMessage;

/** 
 * Client//bl.blImpl.storebl//StoreWarningChecker.java
 * @author CXWorks
 * @date 2015年12月8日 上午12:38:32
 * @version 1.0 
 */
public class StoreWarningChecker {
	private double warningLine;
	public StoreWarningChecker(double line){
		this.warningLine=line;
	}
	//
	public OperationMessage checkOperation(){
		
		return new OperationMessage();
	}
	public double getWarningLine(){
		return warningLine;
	}
}
