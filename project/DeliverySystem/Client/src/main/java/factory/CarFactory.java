package factory;

import tool.vopo.VOPOFactory;
import bl.blImpl.manangrbl.CarManage;
import bl.blService.manageblService.ManageblCarService;

public class CarFactory  extends BLFactory{

    private static ManageblCarService manageCarService;

    public static ManageblCarService getCarService(){
        if(manageCarService == null){
        	manageCarService = new CarManage(vopoFactory);
        }
        return manageCarService;
    }
}