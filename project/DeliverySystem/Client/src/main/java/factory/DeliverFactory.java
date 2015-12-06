package factory;

import tool.draft.DraftController;
import tool.draft.DraftService;
import tool.vopo.VOPOFactory;
import bl.blImpl.deliverbl.DeliverBLImpl;
import bl.blImpl.formatCheck.FormatCheckImpl;
import bl.blService.FormatCheckService.FormatCheckService;
import bl.blService.deliverblService.DeliverBLService;

public class DeliverFactory extends BLFactory{

	private static DeliverBLService deliverBLService;
	private static FormatCheckService formatCheckService = new FormatCheckImpl();
	
	private DeliverFactory(){}

	public static DeliverBLService getDeliverBLService() {
		if(deliverBLService == null){
			deliverBLService = new DeliverBLImpl(vopoFactory,draftService,formatCheckService);
		}
		return deliverBLService;
	}

	
}
