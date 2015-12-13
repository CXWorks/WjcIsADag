package bl.blImpl.initbl;

import bl.blService.financeblService.BankAccountBLService;
import bl.blService.initblService.InitializationBLService;
import bl.blService.manageblService.ManageblCarService;
import bl.blService.manageblService.ManageblCenterService;
import bl.blService.manageblService.ManageblHallService;
import bl.blService.manageblService.ManageblStaffService;
import bl.blService.storeblService.StoreModelBLService;
import bl.clientNetCache.CacheHelper;
import message.CheckFormMessage;
import message.OperationMessage;
import model.store.StoreArea;
import model.store.StoreAreaCode;
import model.store.StoreLocation;
import model.store.StoreModel;
import po.InfoEnum;
import po.initialdata.InitialDataPO;
import po.memberdata.StaffTypeEnum;
import rmi.initialdata.InitialDataService;
import tool.vopo.VOPOFactory;
import userinfo.UserInfo;
import vo.accountvo.AccountVO;
import vo.configurationvo.City2DVO;
import vo.configurationvo.ConfigurationVO;
import vo.configurationvo.PackVO;
import vo.configurationvo.PriceVO;
import vo.configurationvo.ProportionVO;
import vo.configurationvo.SalaryStrategyVO;
import vo.financevo.BankAccountVO;
import vo.financevo.PaymentVO;
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
    String version;

    // manageServices
    InitialDataService initialDataService;
    
    public InitializationBLController(VOPOFactory vopoFactory){
    	this.vopoFactory=vopoFactory;
    	this.initialDataService=CacheHelper.getInitialDataService();
    	try {
			version=this.initialDataService.getLatest_version(UserInfo.getUserID());
			System.out.println(version);
			InitialDataPO initialDataPO=this.initialDataService.getInitialDataPO(version);
			this.initialDataVO=(InitialDataVO)vopoFactory.transPOtoVO(initialDataPO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

    
    public ArrayList<CarVO> getCar(String itself) {
    	ArrayList<CarVO> result =initialDataVO.getCars();
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

    public ArrayList<StaffVO> getStaff(StaffTypeEnum staffTypeEnum) {
    	ArrayList<StaffVO> result =initialDataVO.getStaffs();
		return result;
    }

    public ArrayList<StaffVO> getStaffByInstitution(String hallID) {
    	ArrayList<StaffVO> result =new ArrayList<StaffVO>();
    	List<StaffVO> src=initialDataVO.getStaffs();
    	for (StaffVO staffVO : src) {
			if (staffVO.getInstitutionID().equalsIgnoreCase(hallID)) {
				result.add(staffVO);
			}
		}
		return result;
    }

    public OperationMessage modifyStaff(StaffVO after) {
        this.dismissStaff(after);
        this.addStaff(after);
        return new OperationMessage();
    }

    public OperationMessage addStaff(StaffVO staff) {
        boolean re=initialDataVO.getStaffs().add(staff);
        return new OperationMessage(re, null);
    }

    public OperationMessage dismissStaff(StaffVO staff) {
        boolean re=initialDataVO.getStaffs().remove(staff);
        return new OperationMessage(re, null);
    }

    public ArrayList<CenterVO> getCenter() {
    	ArrayList<CenterVO> result =initialDataVO.getCenters();
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

    public ArrayList<HallVO> getHall() {
    	ArrayList<HallVO> result =initialDataVO.getHalls();
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

    public InitialDataVO getInitialDataVO() {
       try {
		String version=initialDataService.getLatest_version(UserInfo.getUserID());
		InitialDataPO initialDataPO=initialDataService.getInitialDataPO(version);
		this.initialDataVO=(InitialDataVO)vopoFactory.transPOtoVO(initialDataPO);
		return this.initialDataVO;
	} catch (RemoteException e) {
		e.printStackTrace();
		return null;
	}
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
			e.printStackTrace();
			return new OperationMessage(false, e.getMessage());
		}
    }

    public OperationMessage abortInitData() {
       try {
		return initialDataService.abortInitData(UserInfo.getUserID());
	} catch (RemoteException e) {
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
	public OperationMessage reducePartition(String modelID,StoreAreaCode area, int shelfNumber) {
		StoreModel src=this.searchModel(modelID);
		return src.reducePartition(area, shelfNumber);
	}

	/* (non-Javadoc)
	 * @see bl.blService.initblService.InitializationBLService#expandPartition(model.store.StoreAreaCode, int)
	 */
	@Override
	public OperationMessage expandPartition(String modelID,StoreAreaCode area, int shelfNumber) {
		StoreModel src=this.searchModel(modelID);
		return src.expandPartition(area, shelfNumber);
	}

	/* (non-Javadoc)
	 * @see bl.blService.initblService.InitializationBLService#moveShelf(model.store.StoreAreaCode, int, int, model.store.StoreAreaCode, int, int)
	 */
	@Override
	public OperationMessage moveShelf(String modelID,StoreAreaCode code_now, int row_now,
			int shelf_now, StoreAreaCode code, int row, int shelf) {
		StoreModel storeModel=this.searchModel(modelID);
		return storeModel.moveShelf(code_now, row_now, shelf_now, code, row, shelf);
	}

	/* (non-Javadoc)
	 * @see bl.blService.initblService.InitializationBLService#getShelfInfo(model.store.StoreAreaCode)
	 */
	@Override
	public ArrayList<StoreShelfVO> getShelfInfo(String modelID,StoreAreaCode storeAreaCode) {
		StoreModel storeModel=this.searchModel(modelID);
		StoreArea area=storeModel.getArea(storeAreaCode);
		int totalShelf=area.getShelfNumber();
		int totalRow=area.getRowNumber();
		ArrayList<StoreShelfVO> storeShelfVOs=new ArrayList<StoreShelfVO>(totalShelf);
		for(int i=1;i<=totalRow;i++){
			for (int j = 1; j <=totalShelf; j++) {
				ArrayList<StoreLocation> storeLocations=area.getByShelf(i, j);
				double usedProportion=storeLocations.size()/50.0;
				StoreShelfVO storeShelfVO=new StoreShelfVO(i, j, usedProportion);
				storeShelfVOs.add(storeShelfVO);
			}
		}
		return storeShelfVOs;
	}

	/* (non-Javadoc)
	 * @see bl.blService.initblService.InitializationBLService#getStoreAreaInfo(model.store.StoreAreaCode)
	 */
	@Override
	public StoreAreaInfoVO getStoreAreaInfo(String modelID,StoreAreaCode storeAreaCode) {
		StoreModel storeModel=this.searchModel(modelID);
		StoreArea storeArea=storeModel.getArea(storeAreaCode);
		StoreAreaInfoVO storeAreaInfoVO=new StoreAreaInfoVO(storeArea);
		return storeAreaInfoVO;
	}

	/* (non-Javadoc)
	 * @see bl.blService.initblService.InitializationBLService#searchModel(java.lang.String)
	 */
	@Override
	public StoreModel searchModel(String modelID) {
		List<StoreModel> storeModels=initialDataVO.getStoreModels();
		for (int i = 0; i < storeModels.size(); i++) {
			StoreModel storeModel=storeModels.get(i);
			if (storeModel.getCenterID().equalsIgnoreCase(modelID)) {
				return storeModel;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.financeblService.BankAccountBLService#getTradeHistory(vo.financevo.BankAccountVO)
	 */
	@Override
	public List<PaymentVO> getTradeHistory(BankAccountVO avo) {
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.financeblService.BankAccountBLService#pay(java.lang.String, java.lang.String)
	 */
	@Override
	public OperationMessage pay(String bankAccID, String amount) {
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.financeblService.BankAccountBLService#receive(java.lang.String, java.lang.String)
	 */
	@Override
	public OperationMessage receive(String bankAccID, String amount) {
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.storeblService.StoreModelBLService#setWarningLine(double)
	 */
	@Override
	public OperationMessage setWarningLine(double percent) {
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.storeblService.StoreModelBLService#getWarningLine()
	 */
	@Override
	public double getWarningLine() {
		return 0;
	}

	
	@Override
	public CarVO searchCar(String car) {
    	List<CarVO> carVOs=initialDataVO.getCars();
    	for (CarVO carVO : carVOs) {
			if (carVO.getCarID().equalsIgnoreCase(car)) {
				return carVO;
			}
		}
    	return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.manageblService.ManageblCarService#newCarID()
	 */
	@Override
	public String newCarID() {
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.manageblService.ManageblStaffService#searchStaff(java.lang.String)
	 */
	@Override
	public StaffVO searchStaff(String staffID) {
		List<StaffVO> staffVOs=initialDataVO.getStaffs();
		for (StaffVO staffVO : staffVOs) {
			if (staffVO.getID().equalsIgnoreCase(staffID)) {
				return staffVO;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.manageblService.ManageblStaffService#newStaffID(po.memberdata.StaffTypeEnum, java.lang.String)
	 */
	@Override
	public String newStaffID(StaffTypeEnum staffType, String unitID) {
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.manageblService.ManageblCenterService#searchCenter(java.lang.String)
	 */
	@Override
	public CenterVO searchCenter(String centerID) {
    	List<CenterVO> src=initialDataVO.getCenters();
    	for (CenterVO centerVO : src) {
			if (centerVO.getCenterID().equalsIgnoreCase(centerID)) {
				return centerVO;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.manageblService.ManageblCenterService#newCenterID(java.lang.String)
	 */
	@Override
	public String newCenterID(String city) {
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.manageblService.ManageblHallService#searchHall(java.lang.String)
	 */
	@Override
	public HallVO searchHall(String hallID) {
    	List<HallVO> src=initialDataVO.getHalls();
    	for (HallVO hallVO : src) {
			if (hallVO.getHallID().equalsIgnoreCase(hallID)) {
				return hallVO;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.manageblService.ManageblHallService#newHallID(java.lang.String)
	 */
	@Override
	public String newHallID(String centerID) {
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLManageService#getAccountVOs()
	 */
	@Override
	public ArrayList<AccountVO> getAccountVOs() {
		return initialDataVO.getAccountVOs();
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLManageService#getAccountVO(java.lang.String)
	 */
	@Override
	public AccountVO getAccountVO(String accountID) {
		ArrayList<AccountVO> src=initialDataVO.getAccountVOs();
		for (AccountVO accountVO : src) {
			if (accountVO.ID.equalsIgnoreCase(accountID)) {
				return accountVO;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLManageService#addAccount(vo.accountvo.AccountVO)
	 */
	@Override
	public OperationMessage addAccount(AccountVO vo) {
		boolean re=initialDataVO.getAccountVOs().add(vo);
		return new OperationMessage(re, null);
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLManageService#deleteAccount(vo.accountvo.AccountVO)
	 */
	@Override
	public OperationMessage deleteAccount(AccountVO vo) {
		boolean re=initialDataVO.getAccountVOs().remove(vo);
		return new OperationMessage(re, null);
	}

	/* (non-Javadoc)
	 * @see bl.blService.accountblService.AccountBLManageService#modifyAccount(vo.accountvo.AccountVO)
	 */
	@Override
	public OperationMessage modifyAccount(AccountVO vo) {
		this.deleteAccount(vo);
		this.addAccount(vo);
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see bl.blService.configurationblService.ConfigurationBLService#get(po.InfoEnum)
	 */
	@Override
	public ArrayList<ConfigurationVO> get(InfoEnum type) {
		ArrayList<ConfigurationVO> ans=new ArrayList<ConfigurationVO>();
		switch (type) {
		case PACK:
			ans.add(initialDataVO.getPackVO());
			return ans;
		case PROPORTION:
			ans.add(initialDataVO.getProportionVO());
			return ans;
		case PRICE:
			ans.add(initialDataVO.getPriceVO());
			return ans;
		case SALARY:
			ans.addAll(initialDataVO.getSalaryStrategyVO());
			return ans;
		case CITY_2D:
			ans.addAll(initialDataVO.getCity2dvos());
			return ans;

		default:
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.configurationblService.ConfigurationBLService#modify(vo.configurationvo.ConfigurationVO)
	 */
	@Override
	public OperationMessage modify(ConfigurationVO after) {
		switch (after.getInfoEnum()) {
		case PRICE:
			PriceVO priceVO=(PriceVO)after;
			return initialDataVO.setPriceVO(priceVO);
		case PROPORTION:
			ProportionVO proportionVO=(ProportionVO)after;
			return initialDataVO.setProportionVO(proportionVO);
		case PACK:
			PackVO packVO=(PackVO)after;
			return initialDataVO.setPackVO(packVO);
		case SALARY:
			SalaryStrategyVO salaryStrategyVO=(SalaryStrategyVO)after;
			ArrayList<SalaryStrategyVO> salaryStrategyVOs=initialDataVO.getSalaryStrategyVO();
			for (SalaryStrategyVO salaryStrategyVO2 : salaryStrategyVOs) {
				if (salaryStrategyVO2.getStaff().equals(salaryStrategyVO.getStaff())) {
					salaryStrategyVO2.setBase(salaryStrategyVO.getBase());
					salaryStrategyVO2.setBonus(salaryStrategyVO.getBonus());
					salaryStrategyVO2.setCommission(salaryStrategyVO.getCommission());
					return new OperationMessage();
				}
			}
			return new OperationMessage(false, "not found");
		case CITY_2D:
			City2DVO city2dvo=(City2DVO)after;
			ArrayList<City2DVO> city2dvos=initialDataVO.getCity2dvos();
			for (City2DVO city2dvo2 : city2dvos) {
				if (city2dvo2.getName().equals(city2dvo.getName())) {
					city2dvo2.setX(city2dvo.getX());
					city2dvo2.setY(city2dvo.getY());
					return new OperationMessage();
				}
			}
			return new OperationMessage(false,"not found");
		default:
			return new OperationMessage(false, "unknown type");
		}
	}

	/* (non-Javadoc)
	 * @see bl.blService.initblService.InitializationBLService#addRows(java.lang.String, model.store.StoreAreaCode, java.lang.String, java.lang.String)
	 */
	@Override
	public void addRows(String modelID, StoreAreaCode anEnum, String rowNum,
			String shelvesNum) {
		StoreModel storeModel=this.searchModel(modelID);
		StoreArea storeArea=storeModel.getArea(anEnum);
		storeArea.addRows(rowNum, shelvesNum);
		return ;
		
	}

	/* (non-Javadoc)
	 * @see bl.blService.initblService.InitializationBLService#addShelves(java.lang.String, model.store.StoreAreaCode, java.lang.String, java.lang.String)
	 */
	@Override
	public void addShelves(String modelID, StoreAreaCode anEnum, String rowNum,
			String shelvesNum) {
		// TODO Auto-generated method stub
		StoreModel storeModel=this.searchModel(modelID);
		StoreArea storeArea=storeModel.getArea(anEnum);
		storeArea.addShelves(rowNum, shelvesNum);
		
	}
}
