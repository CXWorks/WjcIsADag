package factory;


import bl.blImpl.deliverbl.CheckDeliverImpl;
import bl.blImpl.deliverbl.DeliverBLImpl;
import bl.blImpl.formatCheck.FormatCheckImpl;
import bl.blService.FormatCheckService.FormatCheckService;
import bl.blService.deliverblService.CheckDeliverForm;
import bl.blService.deliverblService.DeliverBLService;

public class DeliverFactory extends BLFactory{

	private static DeliverBLService deliverBLService;
	private static CheckDeliverForm checkDeliverForm;
	private static FormatCheckService formatCheckService = new FormatCheckImpl();
	
	private DeliverFactory(){}

	public static DeliverBLService getDeliverBLService() {
		if(deliverBLService == null){
			deliverBLService = new DeliverBLImpl(vopoFactory,draftService,formatCheckService);
		}
		return deliverBLService;
	}

	public static CheckDeliverForm getCheckDeliverForm(){
		if(checkDeliverForm==null){
			checkDeliverForm = new CheckDeliverImpl(vopoFactory);
		}
		return checkDeliverForm;
	}
	
}
