package vo.initialdata;

import model.store.StoreModel;
import tool.vopo.VOPOFactory;
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
    List<City2DVO> city2dvos;
    PriceVO priceVO;
    ProportionVO proportionVO;
    List< SalaryStrategyVO> salaryStrategyVO;
    PackVO packVO;
    List<AccountVO> accountVOs;
//    public static void main(String[] args) {
//		InitialDataVO i=new InitialDataVO("1", "dd", new ArrayList<StoreModel>(), new ArrayList<BankAccountVO>(), new ArrayList<CarVO>(), new ArrayList<StaffVO>(), new ArrayList<HallVO>(), new ArrayList<CenterVO>(), new PriceVO(), new ProportionVO(), new ArrayList<SalaryStrategyVO>(), new PackVO(), new ArrayList<AccountVO>(),new ArrayList<City2DVO>());
//		InitialDataPO p=i.toPO();
//	}

    public InitialDataVO(){
    	super(InfoEnum.INITIAL_DATA);
    }
    public InitialDataVO(InitialDataPO po){
    	this();
    	this.bankAccounts=(ArrayList<BankAccountVO>) this.trans((ArrayList<? extends CommonPO>) po.getBankAccounts());
    	this.cars=(ArrayList<CarVO>) this.trans((ArrayList<? extends CommonPO>) po.getCars());
    	this.staffs=(ArrayList<StaffVO>) this.trans((ArrayList<? extends CommonPO>) po.getStaffs());
    	this.halls=(ArrayList<HallVO>)this.trans((ArrayList<? extends CommonPO>) po.getHalls());
    	this.centers=(ArrayList<CenterVO>) this.trans((ArrayList<? extends CommonPO>) po.getCenters());
    	this.priceVO=new PriceVO(po.getPricePO());
    	this.proportionVO=new ProportionVO(po.getProportionPO());
    	for(SalaryStrategyPO tmp:po.getSalaryStrategyPO()){
    		this.salaryStrategyVO.add( new SalaryStrategyVO(tmp));
    	}
    	this.packVO=new PackVO(po.getPackPO());
    	this.accountVOs=(List<AccountVO>) this.trans((ArrayList<? extends CommonPO>) po.getAccountPOs());
    	this.city2dvos=(List<City2DVO>) this.trans((ArrayList<? extends CommonPO>) po.getCity2dpos());
    }

    private ArrayList<? extends CommonVO> trans(ArrayList<? extends CommonPO> src){
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
    	List< SalaryStrategyPO> salaryStrategyPO = new ArrayList< SalaryStrategyPO>();
    	for(SalaryStrategyVO tmp:this.salaryStrategyVO){
    		salaryStrategyPO.add( tmp.toPO());
    	}
    	return new InitialDataPO(version, dbName, storeModels, bankAccountPOs, carPOs, staffPOs, hallPOs, centerPOs, city2dpos, priceVO.toPO(), proportionVO.toPO(), salaryStrategyPO, packVO.toPO(), accountPOs);
    }



    //

	public String getVersion() {
		return version;
	}
	/**
	 * @param infoEnum
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
			PackVO packVO, List<AccountVO> accountVOs,ArrayList<City2DVO> city2dvos) {
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
	public List<SalaryStrategyVO> getSalaryStrategyVO() {
		return salaryStrategyVO;
	}
	public PackVO getPackVO() {
		return packVO;
	}
	public List<AccountVO> getAccountVOs() {
		return accountVOs;
	}

}
