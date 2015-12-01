package factory;

import bl.blImpl.deliverbl.DeliverBLImpl;
import bl.blImpl.formatCheck.FormatCheckImpl;
import bl.blImpl.orderbl.OrderBLController;
import bl.blImpl.receivebl.ReceiveblImpl;
import bl.blImpl.storebl.StoreIOBLImpl;
import bl.blImpl.storebl.StoreInBLImpl;
import bl.blImpl.storebl.StoreOutBLImpl;
import bl.blImpl.transportbl.TransportCenterBLImpl;
import bl.blImpl.transportbl.TransportHallBLImpl;
import bl.blService.FormatCheckService.FormatCheckService;
import bl.blService.deliverblService.DeliverBLService;
import bl.blService.orderblService.OrderBLService;
import bl.blService.receiveblService.ReceiveBLService;
import bl.blService.storeblService.StoreIOBLService;
import bl.blService.storeblService.StoreInBLService;
import bl.blService.storeblService.StoreOutBLService;
import bl.blService.transportblService.TransportCenterBLService;
import bl.blService.transportblService.TransportHallBLService;
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
	private static TransportCenterBLService transportCenterBLService;
	private static TransportHallBLService transportHallBLService;
	private static StoreInBLService storeInBLService;
	private static StoreOutBLService storeOutBLService;
	private static StoreIOBLService storeIOBLService;

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
	public static DeliverBLService getDeliverBLService() {
		if(deliverBlService == null){
			deliverBlService = new DeliverBLImpl(vopoFactory, draftService, formatCheckService);
		}
		return deliverBlService;
	}

	public static TransportCenterBLService getTransportCenterBLService() {
		if(transportCenterBLService == null){
			transportCenterBLService = new TransportCenterBLImpl(vopoFactory, draftService);
		}
		return transportCenterBLService;
	}

	public static TransportHallBLService getTransportHallBLService() {
		if(transportHallBLService == null){
			transportHallBLService = new TransportHallBLImpl(vopoFactory, draftService);
		}
		return transportHallBLService;
	}
	public static StoreInBLService getStoreInBLService() {
		if(storeInBLService == null){
			storeInBLService = new StoreInBLImpl(vopoFactory, draftService);
		}
		return storeInBLService;
	}
	public static StoreOutBLService getStoreOutBLService() {
		if(storeOutBLService == null){
			storeOutBLService = new StoreOutBLImpl(vopoFactory, draftService);
		}
		return storeOutBLService;
	}
	public static StoreIOBLService getStoreIOBLService() {
		if(storeIOBLService == null){
			storeIOBLService = new StoreIOBLImpl(vopoFactory);
		}
		return storeIOBLService;
	}
}
