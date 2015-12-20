package bl.blService.initblService;

import bl.blService.accountblService.AccountBLManageService;
import bl.blService.configurationblService.ConfigurationBLService;
import bl.blService.financeblService.BankAccountBLService;
import bl.blService.manageblService.ManageblCarService;
import bl.blService.manageblService.ManageblCenterService;
import bl.blService.manageblService.ManageblDriverService;
import bl.blService.manageblService.ManageblHallService;
import bl.blService.manageblService.ManageblStaffService;
import bl.blService.storeblService.StoreModelBLService;
import message.OperationMessage;
import model.store.StoreAreaCode;
import model.store.StoreModel;
import po.financedata.BankAccountPO;
import po.initialdata.InitialDataPO;
import vo.financevo.BankAccountVO;
import vo.financevo.PaymentVO;
import vo.initialdata.InitialDataVO;
import vo.managevo.car.CarVO;
import vo.managevo.institution.CenterVO;
import vo.managevo.institution.HallVO;
import vo.managevo.staff.StaffVO;
import vo.storevo.StoreAreaInfoVO;
import vo.storevo.StoreShelfVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * combine the services to reuse the codes, problem may occur
 * Created by Sissel on 2015/10/26.
 */

public interface InitializationBLService extends BankAccountBLService,
		StoreModelBLService, ManageblCarService, ManageblStaffService, 
		ManageblCenterService, ManageblHallService, ConfigurationBLService, 
		AccountBLManageService,ManageblDriverService
{
    // Store Manage
    
    public StoreModel searchModel(String modelID);
    
    public List<StoreModel> getAllStoreModels();
    
    // Initial Data Manage
    public InitialDataVO getInitialDataVO();

    public OperationMessage requestInitData();

    public OperationMessage uploadInitialData();

    public OperationMessage abortInitData();

    /**
     *
     * @param modelID 仓库号
     * @param anEnum 选区
     * @param rowNum 要加的排的数目
     * @param shelvesNum 每排的架子数
     */
    void addRows(String modelID, StoreAreaCode anEnum, String rowNum, String shelvesNum);

    /**
     *
     * @param modelID 仓库号
     * @param anEnum 选区
     * @param rowNum 加在哪一排
     * @param shelvesNum 加多少架子
     */
    void addShelves(String modelID, StoreAreaCode anEnum, String rowNum, String shelvesNum);
}
