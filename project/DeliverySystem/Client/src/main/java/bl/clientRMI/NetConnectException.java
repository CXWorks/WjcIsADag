package bl.clientRMI;

/** 
 * Client//bl.clientRMI//NetConnectException.java
 * @author CXWorks
 * @date Dec 27, 2015 1:32:28 PM
 * @version 1.0 
 */
public class NetConnectException extends NetException {
	public NetConnectException(Throwable e,String reason){
		super(e, reason);
	}
}
