package factory;

import bl.blImpl.formatCheck.FormatCheckImpl;
import bl.blImpl.orderbl.OrderBLController;
import bl.blImpl.receivebl.ReceiveblImpl;
import bl.blService.FormatCheckService.FormatCheckService;
import bl.blService.deliverblService.DeliverBLService;
import bl.blService.orderblService.OrderBLService;
import bl.blService.receiveblService.ReceiveBLService;
import rmi.deliverdata.DeliverDataService;
import tool.draft.DraftController;
import tool.draft.DraftService;
import tool.vopo.VOPOFactory;

/** 
 * Client//factory//FormFactory.java
 * @author CXWorks
 * @date 2015年11月21日 下午11:25:51
 * @version 1.0 
 */
public class FormFactory extends BLFactory {
	//
	private static DraftService draftService = new DraftController();
	private static VOPOFactory vopoFactory = new VOPOFactory();
	private static FormatCheckService formatCheckService = new FormatCheckImpl();
	//
	private static ReceiveBLService receiveBLService;
	private static OrderBLService orderBLService;
	private static DeliverBLService deliverBlService;

	private FormFactory(){

	}

	public static ReceiveBLService getReceiveBLService() {
		if(receiveBLService == null){
			receiveBLService = new ReceiveblImpl(vopoFactory, draftService, formatCheckService);
		}
		return receiveBLService;
	}

	public static OrderBLService getOrderBLService() {
		if(orderBLService == null){
			orderBLService = new OrderBLController(vopoFactory, draftService, formatCheckService);
		}
		return orderBLService;
	}
	
}
