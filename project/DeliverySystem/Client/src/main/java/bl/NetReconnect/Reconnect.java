package bl.NetReconnect;

import message.OperationMessage;

/** 
 * Client//bl.NetReconnect//Reconnect.java
 * @author CXWorks
 * @date Dec 27, 2015 11:33:57 PM
 * @version 1.0 
 */
public class Reconnect implements Runnable{
	
	public static OperationMessage reconnect(){
		
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
