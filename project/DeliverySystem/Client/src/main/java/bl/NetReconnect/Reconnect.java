package bl.NetReconnect;

import java.io.IOException;
import java.rmi.RemoteException;



import javafx.application.Platform;
import rmi.accountdata.AccountDataService;
import ui.connectui.ConnectDialogController;
import userinfo.UserInfo;
import bl.blService.accountblService.AccountBLRemindService;
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
	private ConnectDialogController connectDialogController;
	
	public Reconnect() {
		reconnected=false;
		try {

			connectDialogController=ConnectDialogController.newConnectDialog();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Thread thread=new Thread(this);
		thread.start();
		
		
	}
	
	public Reconnect(AccountBLRemindService key){
		reconnected=false;
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Platform.runLater(
				()->connectDialogController.showDialog()
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
