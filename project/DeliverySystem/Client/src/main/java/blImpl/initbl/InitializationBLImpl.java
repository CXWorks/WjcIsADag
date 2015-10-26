package blImpl.initbl;

import blService.financeblService.BankAccountBLService;
import blService.initblService.InitializationBLService;
import blService.manageblService.ManageblCarService;
import blService.manageblService.ManageblCenterService;
import blService.manageblService.ManageblHallService;
import blService.manageblService.ManageblStaffService;
import blService.storeblService.StoreModelBLService;
import message.OperationMessage;
import model.store.StoreAreaCode;
import model.store.StoreModel;
import vo.financevo.BankAccountVO;
import vo.initialdata.InitialDataVO;
import vo.managevo.car.CarVO;
import vo.managevo.institution.CenterVO;
import vo.managevo.institution.HallVO;
import vo.managevo.staff.StaffVO;

import java.util.List;

/**
 * Created by Sissel on 2015/10/26.
 */
public class InitializationBLImpl implements InitializationBLService {
    // models
    InitialDataVO dataVO;

    // manageServices
    StoreModelBLService storeService;
    BankAccountBLService bankService;
    ManageblCarService carService;
    ManageblStaffService staffService;
    ManageblCenterService centerService;
    ManageblHallService hallService;

    public List<BankAccountVO> getAllAccounts() {
        return null;
    }

    public List<BankAccountVO> filterAccounts(List<BankAccountVO> list, String s) {
        return null;
    }

    public OperationMessage addAccount(BankAccountVO avo) {
        return null;
    }

    public OperationMessage deleteAccount(BankAccountVO avo) {
        return null;
    }

    public OperationMessage editAccount(BankAccountVO avo, String newName) {
        return null;
    }

    public List<StoreModel> getAllStoreModels() {
        return null;
    }

    public OperationMessage selectStoreModel(StoreModel model) {
        return null;
    }

    public OperationMessage reducePartition(StoreAreaCode area, int number) {
        return null;
    }

    public OperationMessage expandPartition(StoreAreaCode area, int number) {
        return null;
    }

    public OperationMessage deleteRow(StoreAreaCode area, int rowNum, boolean confirmed) {
        return null;
    }

    public OperationMessage addRow(StoreAreaCode area, int initCapacity) {
        return null;
    }

    public OperationMessage adjustRow(StoreAreaCode area, int rowNum, int newCapacity, boolean confirmed) {
        return null;
    }

    public List<CarVO> getAllCars() {
        return null;
    }

    public List<CarVO> filterCarsByHall(String hallID) {
        return null;
    }

    public OperationMessage addCar(CarVO car) {
        return null;
    }

    public OperationMessage modifyCar(CarVO car) {
        return null;
    }

    public OperationMessage deleteCar(CarVO car) {
        return null;
    }

    public List<StaffVO> getAllStaffs() {
        return null;
    }

    public List<StaffVO> filterStaffsByHall(String hallID) {
        return null;
    }

    public OperationMessage modifyStaff(StaffVO after) {
        return null;
    }

    public OperationMessage addStaff(StaffVO staff) {
        return null;
    }

    public OperationMessage deleteStaff(StaffVO staff) {
        return null;
    }

    public List<CenterVO> getAllCenters() {
        return null;
    }

    public List<CenterVO> filterCentersByNumber(String number) {
        return null;
    }

    public OperationMessage addCenter(CenterVO center) {
        return null;
    }

    public OperationMessage deleteCenter(CenterVO center) {
        return null;
    }

    public OperationMessage modifyCenter(CenterVO center) {
        return null;
    }

    public List<HallVO> getAllHalls() {
        return null;
    }

    public List<HallVO> filterHallsByNumber(String number) {
        return null;
    }

    public OperationMessage addHall(HallVO center) {
        return null;
    }

    public OperationMessage deleteHall(HallVO center) {
        return null;
    }

    public OperationMessage modifyHall(HallVO center) {
        return null;
    }
}
