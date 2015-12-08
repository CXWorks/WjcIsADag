package factory;

import bl.blImpl.initbl.InitializationBLController;
import bl.blService.initblService.InitializationBLService;

/**
 * Created by Sissel on 2015/11/29.
 */
public class InitBLFactory extends BLFactory {

    private static InitializationBLService initializationBLService;

    public static InitializationBLService getInitializationBLService(){
        if(initializationBLService == null){
            initializationBLService = new InitializationBLController(vopoFactory);
        }
        return initializationBLService;
    }

}
