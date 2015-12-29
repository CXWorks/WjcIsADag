package bl.NetReconnect;

import java.io.IOException;
import java.rmi.RemoteException;

import javafx.application.Platform;
import message.OperationMessage;
import rmi.accountdata.AccountDataService;
import ui.connectui.ConnectDialogController;
import userinfo.UserInfo;
import bl.blService.accountblService.AccountBLRemindService;
import bl.clientNetCache.CacheHelper;
import bl.clientRMI.NetInitException;

/** 
 * Client//bl.NetReconnect//ReconnectMe.java
 * @author CXWorks
 * @date Dec 30, 2015 12:40:57 AM
 * @version 1.0 
 */
public class ReconnectMe implements Runnable{
	private boolean reconnected;
	private ConnectDialogController connectDialogController;
	public  static void ReConnectFactory(){
		if (UserInfo.isConnected()) {
			new ReconnectMe();
		}
	}
	
	private ReconnectMe() {
		reconnected=false;
		UserInfo.setConnected(false);
		Thread thread=new Thread(this);
		thread.start();
		
		
		
		
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Platform.runLater(
			()->{
				try {
					connectDialogController=ConnectDialogController.newConnectDialog();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				connectDialogController.showDialog();}
				);
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
						Platform.runLater(
							()->connectDialogController.closeDialog()
								);
						UserInfo.setConnected(true);
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
