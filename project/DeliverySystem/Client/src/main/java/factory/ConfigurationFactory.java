package factory;

import tool.vopo.VOPOFactory;
import bl.blImpl.accountbl.AccountBLLoginImpl;
import bl.blImpl.configurationbl.ConfigurationBLController;
import bl.blService.accountblService.AccountBLLoginService;
import bl.blService.configurationblService.ConfigurationBLService;

public class ConfigurationFactory extends BLFactory{

	private static ConfigurationBLService configurationBLService;
	
	private ConfigurationFactory(){}

	public static ConfigurationBLService getConfigurationBLService() {
		if(configurationBLService == null){
			configurationBLService = new ConfigurationBLController(vopoFactory);
		}
		return configurationBLService;
	}

}
