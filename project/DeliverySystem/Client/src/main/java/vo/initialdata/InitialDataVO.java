package vo.initialdata;

import message.OperationMessage;
import model.store.StoreModel;
import tool.vopo.VOPOFactory;
import userinfo.UserInfo;
import vo.CommonVO;
import vo.InfoVO;
import vo.accountvo.AccountVO;
import vo.configurationvo.City2DVO;
import vo.configurationvo.PackVO;
import vo.configurationvo.PriceVO;
import vo.configurationvo.ProportionVO;
import vo.configurationvo.SalaryStrategyVO;
import vo.financevo.BankAccountVO;
import vo.managevo.car.CarVO;
import vo.managevo.institution.CenterVO;
import vo.managevo.institution.HallVO;
import vo.managevo.staff.DriverVO;
import vo.managevo.staff.StaffVO;

import java.util.ArrayList;
import java.util.List;

import po.CommonPO;
import po.InfoEnum;
import po.accountdata.AccountPO;
import po.companydata.CarPO;
import po.companydata.CenterPO;
import po.companydata.HallPO;
import po.configurationdata.City2DPO;
import po.configurationdata.PackPO;
import po.configurationdata.PricePO;
import po.configurationdata.ProportionPO;
import po.configurationdata.SalaryStrategyPO;
import po.financedata.BankAccountPO;
import po.initialdata.InitialDataPO;
import po.memberdata.DriverPO;
import po.memberdata.StaffPO;

/**
 * Created by Sissel on 2015/10/26.
 */
public class InitialDataVO extends InfoVO{
    String version;
    String dbName;

    ArrayList<StoreModel> storeModels;
    ArrayList<BankAccountVO> bankAccounts;
    ArrayList<CarVO> cars;
    ArrayList<StaffVO> staffs;
    ArrayList<HallVO> halls;
    ArrayList<CenterVO> centers;
    ArrayList<City2DVO> city2dvos;
    ArrayList<DriverVO> drivers;
    PriceVO priceVO;
    ProportionVO proportionVO;
    ArrayList< SalaryStrategyVO> salaryStrategyVO;
    PackVO packVO;
    ArrayList<AccountVO> accountVOs;

    public InitialDataVO(){
    	super(InfoEnum.INITIAL_DATA);
    }
    public InitialDataVO(InitialDataPO po){
    	this();
    	this.bankAccounts=(ArrayList<BankAccountVO>) this.trans(po.getBankAccounts());
    	this.cars=(ArrayList<CarVO>) this.trans(po.getCars());
    	this.staffs=(ArrayList<StaffVO>) this.trans(po.getStaffs());
    	this.halls=(ArrayList<HallVO>)this.trans( po.getHalls());
    	this.centers=(ArrayList<CenterVO>) this.trans( po.getCenters());
    	this.priceVO=new PriceVO(po.getPricePO());
    	this.proportionVO=new ProportionVO(po.getProportionPO());
    	this.salaryStrategyVO = (ArrayList<SalaryStrategyVO>) this.trans( po.getSalaryStrategyPO());
    	this.packVO=new PackVO(po.getPackPO());
    	this.accountVOs=(ArrayList<AccountVO>) this.trans( po.getAccountPOs());
    	this.city2dvos= (ArrayList<City2DVO>) this.trans( po.getCity2dpos());
    	this.drivers=(ArrayList<DriverVO>) this.trans(po.getDriverPOs());
		this.storeModels=new ArrayList<>(po.getStoreModels());
    }

    private ArrayList<? extends CommonVO> trans(List<? extends CommonPO> src){
    	VOPOFactory vopoFactory=new VOPOFactory();
    	ArrayList<CommonVO> ans=new ArrayList<CommonVO>(src.size());
    	for(int i=0;i<src.size();i++){
    		CommonPO temp=src.get(i);
    		ans.add(vopoFactory.transPOtoVO(temp));
    	}
    	return ans;
    }

    private ArrayList<? extends CommonPO> tranVO(ArrayList<? extends CommonVO> src){
    	VOPOFactory vopoFactory=new VOPOFactory();
    	ArrayList<CommonPO> ans=new ArrayList<CommonPO>(src.size());
    	for(int i=0;i<src.size();i++){
    		CommonVO temp=src.get(i);
    		ans.add(vopoFactory.transVOtoPO(temp));
    	}
    	return ans;
    }

    public InitialDataPO toPO(){
    	ArrayList<BankAccountPO> bankAccountPOs=(ArrayList<BankAccountPO>) this.tranVO(bankAccounts);
    	ArrayList<CarPO> carPOs=(ArrayList<CarPO>) this.tranVO(cars);
    	ArrayList<StaffPO> staffPOs=(ArrayList<StaffPO>) this.tranVO(staffs);
    	ArrayList<HallPO> hallPOs=(ArrayList<HallPO>) this.tranVO(halls);
    	ArrayList<CenterPO> centerPOs=(ArrayList<CenterPO>) this.tranVO(centers);
    	ArrayList<AccountPO> accountPOs=(ArrayList<AccountPO>) this.tranVO((ArrayList<? extends CommonVO>) accountVOs);
    	ArrayList<City2DPO> city2dpos=(ArrayList<City2DPO>) this.tranVO((ArrayList<? extends CommonVO>) city2dvos);
    	List<SalaryStrategyPO> salaryStrategyPO=(List<SalaryStrategyPO>) this.tranVO(salaryStrategyVO);
    	List<DriverPO> driverPO=(List<DriverPO>) this.tranVO(drivers);
    	InitialDataPO initialDataPO= new InitialDataPO(version, dbName, storeModels, bankAccountPOs, carPOs, staffPOs, hallPOs, centerPOs, city2dpos, priceVO.toPO(), proportionVO.toPO(), salaryStrategyPO, packVO.toPO(), accountPOs,driverPO);
    	initialDataPO.setCache_OperatorID(UserInfo.getUserID());
    	return initialDataPO;
    }



    //

	public String getVersion() {
		return version;
	}
	/**
	 * @param version
	 * @param dbName
	 * @param storeModels
	 * @param bankAccounts
	 * @param cars
	 * @param staffs
	 * @param halls
	 * @param centers
	 * @param priceVO
	 * @param proportionVO
	 * @param salaryStrategyVO
	 * @param packVO
	 * @param accountVOs
	 */
	public InitialDataVO(String version, String dbName,
			ArrayList<StoreModel> storeModels,
			ArrayList<BankAccountVO> bankAccounts, ArrayList<CarVO> cars,
			ArrayList<StaffVO> staffs, ArrayList<HallVO> halls,
			ArrayList<CenterVO> centers, PriceVO priceVO,
			ProportionVO proportionVO, ArrayList<SalaryStrategyVO> salaryStrategyVO,
			PackVO packVO, ArrayList<AccountVO> accountVOs,ArrayList<City2DVO> city2dvos,ArrayList<DriverVO> driverVOs) {
		this();
		this.version = version;
		this.dbName = dbName;
		this.storeModels = storeModels;
		this.bankAccounts = bankAccounts;
		this.cars = cars;
		this.staffs = staffs;
		this.halls = halls;
		this.centers = centers;
		this.priceVO = priceVO;
		this.proportionVO = proportionVO;
		this.salaryStrategyVO = salaryStrategyVO;
		this.packVO = packVO;
		this.accountVOs = accountVOs;
		this.city2dvos=city2dvos;
		this.drivers=driverVOs;
	}
	public String getDbName() {
		return dbName;
	}
	public ArrayList<StoreModel> getStoreModels() {
		return storeModels;
	}
	public ArrayList<BankAccountVO> getBankAccounts() {
		return bankAccounts;
	}
	public ArrayList<CarVO> getCars() {
		return cars;
	}
	public ArrayList<StaffVO> getStaffs() {
		return staffs;
	}
	public ArrayList<HallVO> getHalls() {
		return halls;
	}
	public ArrayList<CenterVO> getCenters() {
		return centers;
	}
	public PriceVO getPriceVO() {
		return priceVO;
	}
	public ProportionVO getProportionVO() {
		return proportionVO;
	}
	public ArrayList<SalaryStrategyVO> getSalaryStrategyVO() {
		return salaryStrategyVO;
	}
	public PackVO getPackVO() {
		return packVO;
	}
	public ArrayList<AccountVO> getAccountVOs() {
		return accountVOs;
	}
	public ArrayList<City2DVO> getCity2dvos() {
		return city2dvos;
	}
	public OperationMessage setPriceVO(PriceVO priceVO) {
		this.priceVO = priceVO;
		return new OperationMessage();
	}
	public OperationMessage setProportionVO(ProportionVO proportionVO) {
		this.proportionVO = proportionVO;
		return new OperationMessage();
	}
	public OperationMessage setPackVO(PackVO packVO) {
		this.packVO = packVO;
		return new OperationMessage();
	}
	public ArrayList<DriverVO> getDrivers() {
		return drivers;
	}
	

}
