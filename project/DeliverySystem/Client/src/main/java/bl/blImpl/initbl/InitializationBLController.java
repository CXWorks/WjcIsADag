package bl.blImpl.initbl;

import bl.blService.financeblService.BankAccountBLService;
import bl.blService.initblService.InitializationBLService;
import bl.blService.manageblService.ManageblCarService;
import bl.blService.manageblService.ManageblCenterService;
import bl.blService.manageblService.ManageblHallService;
import bl.blService.manageblService.ManageblStaffService;
import bl.blService.storeblService.StoreModelBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import model.store.StoreAreaCode;
import model.store.StoreModel;
import po.initialdata.InitialDataPO;
import rmi.initialdata.InitialDataService;
import tool.vopo.VOPOFactory;
import userinfo.UserInfo;
import vo.financevo.BankAccountVO;
import vo.initialdata.InitialDataVO;
import vo.managevo.car.CarVO;
import vo.managevo.institution.CenterVO;
import vo.managevo.institution.HallVO;
import vo.managevo.staff.StaffVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/26.
 */
public class InitializationBLController implements InitializationBLService {
	private VOPOFactory vopoFactory;
    // models
    InitialDataVO dataVO;
    InitialDataPO lastPO;

    // manageServices
    InitialDataService initialDataService;
    StoreModelBLService storeService;
    BankAccountBLService bankService;
    ManageblCarService carService;
    ManageblStaffService staffService;
    ManageblCenterService centerService;
    ManageblHallService hallService;
    
    public InitializationBLController(VOPOFactory vopoFactory){
    	this.vopoFactory=vopoFactory;
    	
    	
    }

    public List<BankAccountVO> getAllAccounts() {
    	List<BankAccountVO> result =new ArrayList<BankAccountVO>();
    	BankAccountVO stub=new BankAccountVO();
		result.add(stub);
		return result;
    }

    public List<BankAccountVO> filterAccounts(List<BankAccountVO> list, String s) {
    	List<BankAccountVO> result =new ArrayList<BankAccountVO>();
    	BankAccountVO stub=new BankAccountVO();
		result.add(stub);
		return result;
    }

    public OperationMessage addAccount(BankAccountVO avo) {
        return new OperationMessage();
    }

    public OperationMessage deleteAccount(BankAccountVO avo) {
        return new OperationMessage();
    }

    public OperationMessage editAccount(BankAccountVO avo, String newName) {
        return new OperationMessage();
    }

    public List<StoreModel> getAllStoreModels() {
    	List<StoreModel> result =new ArrayList<StoreModel>();
		return result;
    }

    public OperationMessage selectStoreModel(StoreModel model) {
        return new OperationMessage();
    }

    public OperationMessage reducePartition(StoreAreaCode area, int number) {
        return new OperationMessage();
    }

    public OperationMessage expandPartition(StoreAreaCode area, int number) {
        return new OperationMessage();
    }

    public OperationMessage deleteRow(StoreAreaCode area, int rowNum, boolean confirmed) {
        return new OperationMessage();
    }

    public OperationMessage addRow(StoreAreaCode area, int initCapacity) {
        return new OperationMessage();
    }

    public OperationMessage adjustRow(StoreAreaCode area, int rowNum, int newCapacity, boolean confirmed) {
        return new OperationMessage();
    }

    public List<CarVO> getAllCars() {
    	List<CarVO> result =new ArrayList<CarVO>();
    	CarVO stub=new CarVO();
		result.add(stub);
		return result;
    }

    public List<CarVO> filterCarsByHall(String hallID) {
    	List<CarVO> result =new ArrayList<CarVO>();
    	CarVO stub=new CarVO();
		result.add(stub);
		return result;
    }

    public OperationMessage addCar(CarVO car) {
        return new OperationMessage();
    }

    public OperationMessage modifyCar(CarVO car) {
        return new OperationMessage();
    }

    public OperationMessage deleteCar(CarVO car) {
        return new OperationMessage();
    }

    public List<StaffVO> getAllStaffs() {
    	List<StaffVO> result =new ArrayList<StaffVO>();
    	StaffVO stub=new StaffVO();
		result.add(stub);
		return result;
    }

    public List<StaffVO> filterStaffsByHall(String hallID) {
    	List<StaffVO> result =new ArrayList<StaffVO>();
    	StaffVO stub=new StaffVO();
		result.add(stub);
		return result;
    }

    public OperationMessage modifyStaff(StaffVO after) {
        return new OperationMessage();
    }

    public OperationMessage addStaff(StaffVO staff) {
        return new OperationMessage();
    }

    public OperationMessage deleteStaff(StaffVO staff) {
        return new OperationMessage();
    }

    public List<CenterVO> getAllCenters() {
    	List<CenterVO> result =new ArrayList<CenterVO>();
    	CenterVO stub=new CenterVO();
		result.add(stub);
		return result;
    }

    public List<CenterVO> filterCentersByNumber(String number) {
    	List<CenterVO> result =new ArrayList<CenterVO>();
    	CenterVO stub=new CenterVO();
		result.add(stub);
		return result;
    }

    public OperationMessage addCenter(CenterVO center) {
        return new OperationMessage();
    }

    public OperationMessage deleteCenter(CenterVO center) {
        return new OperationMessage();
    }

    public OperationMessage modifyCenter(CenterVO center) {
        return new OperationMessage();
    }

    public List<HallVO> getAllHalls() {
    	List<HallVO> result =new ArrayList<HallVO>();
    	HallVO stub=new HallVO();
		result.add(stub);
		return result;
    }

    public List<HallVO> filterHallsByNumber(String number) {
    	List<HallVO> result =new ArrayList<HallVO>();
    	HallVO stub=new HallVO();
		result.add(stub);
		return result;
    }

    public OperationMessage addHall(HallVO center) {
        return new OperationMessage();
    }

    public OperationMessage deleteHall(HallVO center) {
        return new OperationMessage();
    }

    public OperationMessage modifyHall(HallVO center) {
        return new OperationMessage();
    }

    public InitialDataVO getInitialDataVO(String version) {
        return new InitialDataVO();
    }

    public OperationMessage requestInitData() {
        try {
			OperationMessage re=initialDataService.requestInitData(UserInfo.getUserID());
			String lastVersion=initialDataService.getLatest_version(UserInfo.getUserID());
			this.lastPO=initialDataService.getInitialDataPO(lastVersion);
			return re;
		} catch (RemoteException | ClassNotFoundException e) {
			e.printStackTrace();
			return new OperationMessage(false, e.getMessage());
		}
    }

    public OperationMessage uploadInitialData() {
        return new OperationMessage();
    }

    public OperationMessage abortInitData() {
        return new OperationMessage();

    }
}
