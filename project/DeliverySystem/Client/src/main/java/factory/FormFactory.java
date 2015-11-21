package factory;

import bl.blImpl.receivebl.ReceiveblImpl;
import bl.blService.receiveblService.ReceiveBLService;

/** 
 * Client//factory//FormFactory.java
 * @author CXWorks
 * @date 2015年11月21日 下午11:25:51
 * @version 1.0 
 */
public class FormFactory extends BLFactory {
	public ReceiveBLService receiveBLService;
	public FormFactory(){
		receiveBLService=new ReceiveblImpl();
	}
	public ReceiveBLService getReceiveBLService() {
		return receiveBLService;
	}
	
}
