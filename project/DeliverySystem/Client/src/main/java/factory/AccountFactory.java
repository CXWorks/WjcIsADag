package factory;

import bl.blImpl.accountbl.AccountBLManageImpl;
import bl.blImpl.accountbl.AccountBLRemindImpl;
import bl.blService.accountblService.AccountBLManageService;
import bl.blService.accountblService.AccountBLRemindService;
import tool.vopo.VOPOFactory;

/**
 * Created by Sissel on 2015/11/25.
 */
public class AccountFactory extends BLFactory{

    private static AccountBLManageService manageService;
    private static AccountBLRemindService remindService;

    public static AccountBLManageService getManageService(){
        if(manageService == null){
            manageService = new AccountBLManageImpl(vopoFactory);
        }
        return manageService;
    }
    


    public static AccountBLRemindService getRemindService(){
        if(remindService == null){
        	remindService = new AccountBLRemindImpl();
        }
        return remindService;
    }
    
}
