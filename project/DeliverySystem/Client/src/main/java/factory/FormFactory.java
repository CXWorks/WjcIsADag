package factory;

import bl.blImpl.formatCheck.FormatCheckImpl;
import bl.blImpl.receivebl.ReceiveblImpl;
import bl.blService.FormatCheckService.FormatCheckService;
import bl.blService.receiveblService.ReceiveBLService;
import bl.tool.draft.DraftController;
import bl.tool.draft.DraftService;
import bl.tool.vopo.VOPOFactory;

/** 
 * Client//factory//FormFactory.java
 * @author CXWorks
 * @date 2015年11月21日 下午11:25:51
 * @version 1.0 
 */
public class FormFactory extends BLFactory {
	//
	private DraftService draftService;
	private VOPOFactory vopoFactory;
	private FormatCheckService formatCheckService;
	//
	private ReceiveBLService receiveBLService;
	public FormFactory(){
		draftService=new DraftController();
		vopoFactory=new VOPOFactory();
		formatCheckService=new FormatCheckImpl();
		//
		
		receiveBLService=new ReceiveblImpl(vopoFactory, draftService, formatCheckService);
	}
	public ReceiveBLService getReceiveBLService() {
		return receiveBLService;
	}
	
}
