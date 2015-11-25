package factory;

import bl.blImpl.accountbl.AccountBLManageImpl;
import bl.blService.accountblService.AccountBLManageService;
import tool.vopo.VOPOFactory;

/**
 * Created by Sissel on 2015/11/25.
 */
public class AccountFactory extends BLFactory{

    private static AccountBLManageService manageService;

    public static AccountBLManageService getManageService(){
        if(manageService == null){
            manageService = new AccountBLManageImpl(new VOPOFactory());
        }
        return manageService;
    }
}
