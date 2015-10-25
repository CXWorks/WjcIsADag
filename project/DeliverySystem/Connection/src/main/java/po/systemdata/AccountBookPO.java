package po.systemdata;

import model.store.StoreModel;
import po.companydata.CenterPO;
import po.companydata.HallPO;
import po.companydata.CarPO;
import po.financedata.BankAccountPO;
import po.memberdata.StaffPO;

import java.util.List;

/**
 * Created by Sissel on 2015/10/25.
 */
public class AccountBookPO {

    String version;
    String dbName;

    List<BankAccountPO> bankAccounts;
    List<StoreModel> storeModels;
    List<CarPO> cars;
    List<CenterPO> centers;
    List<HallPO> halls;
    List<StaffPO> staffs;

}
