package factory;

import tool.vopo.VOPOFactory;
import bl.blImpl.manangrbl.DriverManage;
import bl.blImpl.manangrbl.StaffManage;
import bl.blService.manageblService.ManageblDriverService;
import bl.blService.manageblService.ManageblStaffService;

public class StaffFactory  extends BLFactory{

    private static ManageblStaffService manageStaffService;
    private static ManageblDriverService manageblDriverService;

    public static ManageblStaffService getManageService(){
        if(manageStaffService == null){
        	manageStaffService = new StaffManage(vopoFactory);
        }
        return manageStaffService;
    }
    
    public static ManageblDriverService getManageblDriverService(){
    	if (manageblDriverService==null) {
			manageblDriverService=new DriverManage(vopoFactory);
		}
    	return manageblDriverService;
    }
}