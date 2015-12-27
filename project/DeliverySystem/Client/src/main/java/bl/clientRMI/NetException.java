package bl.clientRMI;

/** 
 * Client//bl.clientRMI//NetException.java
 * @author CXWorks
 * @date Dec 27, 2015 1:29:51 PM
 * @version 1.0 
 */
public abstract class NetException extends Exception {
	protected String showMessage;
	protected NetException(Throwable e,String reason){
		super(e);
		this.showMessage=reason;
	}
	public String getShowMessage(){
		return this.showMessage;
	}
}
