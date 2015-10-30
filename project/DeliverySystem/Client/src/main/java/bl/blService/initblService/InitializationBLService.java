package bl.blService.initblService;

import bl.blService.financeblService.BankAccountBLService;
import bl.blService.manageblService.ManageblCarService;
import bl.blService.manageblService.ManageblCenterService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * combine the services to reuse the codes, problem may occur
 * Created by Sissel on 2015/10/26.
 */
public interface InitializationBLService {

    // Bank Account Manage
    public List<BankAccountVO> getAllAccounts();

    public List<BankAccountVO> filterAccounts(List<BankAccountVO> list, String s);

    public OperationMessage addAccount(BankAccountVO avo);

    public OperationMessage deleteAccount(BankAccountVO avo);

    public OperationMessage editAccount(BankAccountVO avo, String newName);

    // Store Manage
    public List<StoreModel> getAllStoreModels();

    public OperationMessage selectStoreModel(StoreModel model);

    public OperationMessage reducePartition(StoreAreaCode area, int number);

    public OperationMessage expandPartition(StoreAreaCode area, int number);

    public OperationMessage deleteRow(StoreAreaCode area, int rowNum, boolean confirmed);

    public OperationMessage addRow(StoreAreaCode area, int initCapacity);

    public OperationMessage adjustRow(StoreAreaCode area, int rowNum, int newCapacity, boolean confirmed);

    // Car Manage
    public List<CarVO> getAllCars();

    public List<CarVO> filterCarsByHall(String hallID);

    public OperationMessage addCar(CarVO car);

    public OperationMessage modifyCar(CarVO car);

    public OperationMessage deleteCar(CarVO car);

    // Staff Manage
    public List<StaffVO> getAllStaffs();

    public List<StaffVO> filterStaffsByHall(String hallID);

    public OperationMessage modifyStaff(StaffVO after);

    public OperationMessage addStaff(StaffVO staff);

    public OperationMessage deleteStaff(StaffVO staff);

    // Center Manage
    public List<CenterVO> getAllCenters();

    public List<CenterVO> filterCentersByNumber(String number);

    public OperationMessage addCenter(CenterVO center);

    public OperationMessage deleteCenter(CenterVO center);

    public OperationMessage modifyCenter(CenterVO center);

    // Hall Manage
    public List<HallVO> getAllHalls();

    public List<HallVO> filterHallsByNumber(String number);

    public OperationMessage addHall(HallVO hall);

    public OperationMessage deleteHall(HallVO hall);

    public OperationMessage modifyHall(HallVO hall);

    // Initial Data Manage
    public InitialDataVO getInitialDataVO(String version);

    public OperationMessage requestInitData();

    public OperationMessage uploadInitialData();

    public OperationMessage abortInitData();

}
