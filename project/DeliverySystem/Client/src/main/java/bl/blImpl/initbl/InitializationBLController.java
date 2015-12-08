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
import vo.storevo.StoreAreaInfoVO;
import vo.storevo.StoreShelfVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/26.
 */
public class InitializationBLController implements InitializationBLService {
	private VOPOFactory vopoFactory;
    // models
    InitialDataVO initialDataVO;
    InitialDataPO lastPO;

    // manageServices
    InitialDataService initialDataService;
//    StoreModelBLService storeService;
//    BankAccountBLService bankService;
//    ManageblCarService carService;
//    ManageblStaffService staffService;
//    ManageblCenterService centerService;
//    ManageblHallService hallService;
    
    public InitializationBLController(VOPOFactory vopoFactory){
    	this.vopoFactory=vopoFactory;
    	
    	
    }

    public List<BankAccountVO> getAllAccounts() {
    	ArrayList<BankAccountVO> bankAccountVOs=initialDataVO.getBankAccounts();
    	return bankAccountVOs;
    }

    public List<BankAccountVO> filterAccounts(List<BankAccountVO> list, String s) {
    	LinkedList<BankAccountVO> ans=new LinkedList<BankAccountVO>();
    	for (BankAccountVO bankAccountVO : list) {
			if (bankAccountVO.quzzySearch(s)) {
				ans.add(bankAccountVO);
			}
		}
    	return ans;
    }

    public OperationMessage addAccount(BankAccountVO avo) {
        boolean re=initialDataVO.getBankAccounts().add(avo);
        return new OperationMessage(re,null);
        
    }

    public OperationMessage deleteAccount(BankAccountVO avo) {
        boolean re=initialDataVO.getBankAccounts().remove(avo);
        return new OperationMessage(re, null);
    }

    public OperationMessage editAccount(BankAccountVO avo, String newName) {
        String ID=avo.bankID;
        ArrayList<BankAccountVO> bankAccountVOs=initialDataVO.getBankAccounts();
        int i;
        for (i = 0; i < bankAccountVOs.size(); i++) {
			if (bankAccountVOs.get(i).bankID.equalsIgnoreCase(ID)) {
				break;
			}
		}
        bankAccountVOs.remove(i);
        bankAccountVOs.add(avo);
        return new OperationMessage();
    }

    
    public List<CarVO> getAllCars() {
    	List<CarVO> result =initialDataVO.getCars();
		return result;
    }

    public List<CarVO> filterCarsByHall(String hallID) {
    	List<CarVO> result =new LinkedList<CarVO>();
    	List<CarVO> carVOs=initialDataVO.getCars();
    	for (CarVO carVO : carVOs) {
			if (carVO.getInstitutionID().equalsIgnoreCase(hallID)) {
				result.add(carVO);
			}
		}
    	return result;
    }

    public OperationMessage addCar(CarVO car) {
        boolean re=initialDataVO.getCars().add(car);
        return new OperationMessage(re, null);
    }

    public OperationMessage modifyCar(CarVO car) {
       this.deleteCar(car);
       this.addCar(car);
       return new OperationMessage();
    }

    public OperationMessage deleteCar(CarVO car) {
        boolean re=initialDataVO.getCars().remove(car);
        return new OperationMessage(re, null);
    }

    public List<StaffVO> getAllStaffs() {
    	List<StaffVO> result =initialDataVO.getStaffs();
		return result;
    }

    public List<StaffVO> filterStaffsByHall(String hallID) {
    	List<StaffVO> result =new ArrayList<StaffVO>();
    	List<StaffVO> src=initialDataVO.getStaffs();
    	for (StaffVO staffVO : src) {
			if (staffVO.getInstitutionID().equalsIgnoreCase(hallID)) {
				result.add(staffVO);
			}
		}
		return result;
    }

    public OperationMessage modifyStaff(StaffVO after) {
        this.deleteStaff(after);
        this.addStaff(after);
        return new OperationMessage();
    }

    public OperationMessage addStaff(StaffVO staff) {
        boolean re=initialDataVO.getStaffs().add(staff);
        return new OperationMessage(re, null);
    }

    public OperationMessage deleteStaff(StaffVO staff) {
        boolean re=initialDataVO.getStaffs().remove(staff);
        return new OperationMessage(re, null);
    }

    public List<CenterVO> getAllCenters() {
    	List<CenterVO> result =initialDataVO.getCenters();
    	return result;
    }

    public List<CenterVO> filterCentersByNumber(String number) {
    	List<CenterVO> result =new ArrayList<CenterVO>();
    	List<CenterVO> src=initialDataVO.getCenters();
    	for (CenterVO centerVO : src) {
			if (centerVO.getCenterID().equalsIgnoreCase(number)) {
				result.add(centerVO);
			}
		}
		return result;
    }

    public OperationMessage addCenter(CenterVO center) {
        boolean re=initialDataVO.getCenters().add(center);
        return new OperationMessage(re, null);
    }

    public OperationMessage deleteCenter(CenterVO center) {
       boolean re=initialDataVO.getCenters().remove(center);
       return new OperationMessage(re,null);
    }

    public OperationMessage modifyCenter(CenterVO center) {
       this.deleteCenter(center);
       this.addCenter(center);
       return new OperationMessage();
    }

    public List<HallVO> getAllHalls() {
    	List<HallVO> result =initialDataVO.getHalls();
		return result;
    }

    public List<HallVO> filterHallsByNumber(String number) {
    	List<HallVO> result =new ArrayList<HallVO>();
    	List<HallVO> src=initialDataVO.getHalls();
    	for (HallVO hallVO : src) {
			if (hallVO.getHallID().equalsIgnoreCase(number)) {
				result.add(hallVO);
			}
		}
		return result;
    }

    public OperationMessage addHall(HallVO center) {
        boolean re=initialDataVO.getHalls().add(center);
        return new OperationMessage(re, null);
    }

    public OperationMessage deleteHall(HallVO center) {
        boolean re=initialDataVO.getHalls().remove(center);
        return new OperationMessage(re, null);
    }

    public OperationMessage modifyHall(HallVO center) {
        this.deleteHall(center);
        this.addHall(center);
        return new OperationMessage();
    }

    public InitialDataVO getInitialDataVO(String version) {
       return this.initialDataVO;
    }

    public OperationMessage requestInitData() {
        try {
			OperationMessage re=initialDataService.requestInitData(UserInfo.getUserID());
			String lastVersion=initialDataService.getLatest_version(UserInfo.getUserID());
			this.lastPO=initialDataService.getInitialDataPO(lastVersion);
			this.initialDataVO=(InitialDataVO)vopoFactory.transPOtoVO(lastPO);
			return re;
		} catch (RemoteException | ClassNotFoundException e) {
			e.printStackTrace();
			return new OperationMessage(false, e.getMessage());
		}
    }

    public OperationMessage uploadInitialData() {
    	InitialDataPO po=(InitialDataPO)vopoFactory.transVOtoPO(initialDataVO);
        try {
			OperationMessage re=initialDataService.uploadInitialData(UserInfo.getUserID(),po);
			return re;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new OperationMessage(false, e.getMessage());
		}
    }

    public OperationMessage abortInitData() {
       try {
		return initialDataService.abortInitData(UserInfo.getUserID());
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return new OperationMessage(false, e.getMessage());
	}

    }

	/* (non-Javadoc)
	 * @see bl.blService.initblService.InitializationBLService#getAllStoreModels()
	 */
	@Override
	public List<StoreModel> getAllStoreModels() {
		List<StoreModel> result=initialDataVO.getStoreModels();
		return result;
	}

	/* (non-Javadoc)
	 * @see bl.blService.initblService.InitializationBLService#reducePartition(model.store.StoreAreaCode, int)
	 */
	@Override
	public OperationMessage reducePartition(StoreAreaCode area, int shelfNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.initblService.InitializationBLService#expandPartition(model.store.StoreAreaCode, int)
	 */
	@Override
	public OperationMessage expandPartition(StoreAreaCode area, int shelfNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.initblService.InitializationBLService#moveShelf(model.store.StoreAreaCode, int, int, model.store.StoreAreaCode, int, int)
	 */
	@Override
	public OperationMessage moveShelf(StoreAreaCode code_now, int row_now,
			int shelf_now, StoreAreaCode code, int row, int shelf) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.initblService.InitializationBLService#getShelfInfo(model.store.StoreAreaCode)
	 */
	@Override
	public ArrayList<StoreShelfVO> getShelfInfo(StoreAreaCode storeAreaCode) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.initblService.InitializationBLService#getStoreAreaInfo(model.store.StoreAreaCode)
	 */
	@Override
	public StoreAreaInfoVO getStoreAreaInfo(StoreAreaCode storeAreaCode) {
		// TODO Auto-generated method stub
		return null;
	}
}
