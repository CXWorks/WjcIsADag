package factory;

import tool.vopo.VOPOFactory;
import bl.blImpl.manangrbl.StaffManage;
import bl.blService.manageblService.ManageblStaffService;

public class StaffFactory  extends BLFactory{

    private static ManageblStaffService manageStaffService;

    public static ManageblStaffService getManageService(){
        if(manageStaffService == null){
        	manageStaffService = new StaffManage(new VOPOFactory());
        }
        return manageStaffService;
    }
}