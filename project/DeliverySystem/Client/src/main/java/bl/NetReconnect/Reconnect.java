package bl.NetReconnect;

import java.rmi.RemoteException;

import rmi.accountdata.AccountDataService;
import userinfo.UserInfo;
import bl.clientNetCache.CacheHelper;
import bl.clientRMI.NetInitException;
import message.OperationMessage;

/** 
 * Client//bl.NetReconnect//Reconnect.java
 * @author CXWorks
 * @date Dec 27, 2015 11:33:57 PM
 * @version 1.0 
 */
public class Reconnect implements Runnable{
	private boolean reconnected;
	
	
	public Reconnect() {
		reconnected=false;
		Thread thread=new Thread(this);
		thread.start();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true&&!reconnected) {
			try {
				Thread.sleep(10000);
				try {
					CacheHelper.reconnect();
					//
					AccountDataService accountDataService=CacheHelper.getAccountDataService();
					OperationMessage op1=accountDataService.setAccount(UserInfo.getUserID(), true);
					if (op1.operationResult) {
						this.reconnected=true;
					}
				} catch (NetInitException | RemoteException e) {
					continue;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
