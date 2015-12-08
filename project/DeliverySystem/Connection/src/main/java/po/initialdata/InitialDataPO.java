package po.initialdata;

import java.io.Serializable;
import java.util.List;

import model.store.StoreModel;
import po.InfoEnum;
import po.InfoPO;
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
import po.memberdata.StaffPO;

/**
 * Created by Sissel on 2015/10/26.
 */
public class InitialDataPO extends InfoPO implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = 2373730774787127089L;

	String version;
    String dbName;

    List<StoreModel> storeModels;
    List<BankAccountPO> bankAccounts;
    List<CarPO> cars;
    List<StaffPO> staffs;
    List<HallPO> halls;
    List<CenterPO> centers;
    List<City2DPO> city2dpos;
    PricePO pricePO;
    ProportionPO proportionPO;
    SalaryStrategyPO salaryStrategyPO;
    PackPO packPO;
    List<AccountPO> accountPOs;

    public void setVersion(String version) {
		this.version = version;
	}

	public InitialDataPO(){
    	super(InfoEnum.INITIAL_DATA);
    }
    //

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getVersion() {
		return version;
	}

	public String getDbName() {
		return dbName;
	}

	public List<StoreModel> getStoreModels() {
		return storeModels;
	}

	public List<BankAccountPO> getBankAccounts() {
		return bankAccounts;
	}

	public List<CarPO> getCars() {
		return cars;
	}

	public List<StaffPO> getStaffs() {
		return staffs;
	}

	public List<HallPO> getHalls() {
		return halls;
	}

	public List<CenterPO> getCenters() {
		return centers;
	}

	public List<City2DPO> getCity2dpos() {
		return city2dpos;
	}

	public PricePO getPricePO() {
		return pricePO;
	}

	public ProportionPO getProportionPO() {
		return proportionPO;
	}

	public SalaryStrategyPO getSalaryStrategyPO() {
		return salaryStrategyPO;
	}

	public PackPO getPackPO() {
		return packPO;
	}

	public List<AccountPO> getAccountPOs() {
		return accountPOs;
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
	 * @param city2dpos
	 * @param pricePO
	 * @param proportionPO
	 * @param salaryStrategyPO
	 * @param packPO
	 * @param accountPOs
	 */
	public InitialDataPO(String version, String dbName,
			List<StoreModel> storeModels, List<BankAccountPO> bankAccounts,
			List<CarPO> cars, List<StaffPO> staffs, List<HallPO> halls,
			List<CenterPO> centers, List<City2DPO> city2dpos, PricePO pricePO,
			ProportionPO proportionPO, SalaryStrategyPO salaryStrategyPO,
			PackPO packPO, List<AccountPO> accountPOs) {
		this();
		this.version = version;
		this.dbName = dbName;
		this.storeModels = storeModels;
		this.bankAccounts = bankAccounts;
		this.cars = cars;
		this.staffs = staffs;
		this.halls = halls;
		this.centers = centers;
		this.city2dpos = city2dpos;
		this.pricePO = pricePO;
		this.proportionPO = proportionPO;
		this.salaryStrategyPO = salaryStrategyPO;
		this.packPO = packPO;
		this.accountPOs = accountPOs;
	}
    
}
