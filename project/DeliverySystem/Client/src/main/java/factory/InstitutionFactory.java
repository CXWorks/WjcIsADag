package factory;

import bl.blImpl.manangrbl.CenterManage;
import bl.blImpl.manangrbl.HallManage;
import bl.blService.manageblService.ManageblCenterService;
import bl.blService.manageblService.ManageblHallService;

/** 
 * Client//factory//InstitutionFactory.java
 * @author CXWorks
 * @date 2015年12月8日 上午9:44:00
 * @version 1.0 
 */
public class InstitutionFactory extends BLFactory {
	private static ManageblHallService manageblHallService;
	private static ManageblCenterService manageblCenterService;
	public static ManageblHallService getManageblHallService() {
		if (manageblHallService==null) {
			manageblHallService=new HallManage(vopoFactory);
		}
		return manageblHallService;
	}
	public static ManageblCenterService getManageblCenterService() {
		if (manageblCenterService==null) {
			manageblCenterService=new CenterManage(vopoFactory);
		}
		return manageblCenterService;
	}
	
}
