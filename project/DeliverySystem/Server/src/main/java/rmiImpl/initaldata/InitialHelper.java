package rmiImpl.initaldata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConnecterHelper;
import message.OperationMessage;
import model.store.StoreAreaCode;
import model.store.StoreModel;
import po.accountdata.AccountPO;
import po.companydata.CarPO;
import po.companydata.CenterPO;
import po.companydata.HallPO;
import po.configurationdata.City2DPO;
import po.configurationdata.PricePO;
import po.financedata.BankAccountPO;
import po.initialdata.InitialDataPO;
import po.memberdata.DriverPO;
import po.memberdata.StaffPO;
import po.memberdata.StaffTypeEnum;
import rmi.accountdata.AccountDataService;
import rmi.companydata.CompanyDataCarService;
import rmi.companydata.CompanyDataCenterService;
import rmi.companydata.CompanyDataHallService;
import rmi.configurationdata.ConfigurationDataService;
import rmi.financedata.BankAccountDataService;
import rmi.memberdata.MemberDataService;
import rmi.storedata.StoreModelDataService;
import rmiImpl.accountdata.AccountDataImpl;
import rmiImpl.companydata.CompanyDataCarImpl;
import rmiImpl.companydata.CompanyDataCenterImpl;
import rmiImpl.companydata.CompanyDataHallImpl;
import rmiImpl.configurationdata.ConfigurationDataImpl;
import rmiImpl.financedata.BankAccountDataImpl;
import rmiImpl.memberdata.DriverDataImpl;
import rmiImpl.memberdata.StaffDataImpl;
import rmiImpl.storedata.StoreModelDataImpl;

public class InitialHelper {
	private static final String ROOT = "initial/";
	private Connection conn = ConnecterHelper.getConn();
	private PreparedStatement statement = null;

	private StoreModelDataService storeModelDataService = new StoreModelDataImpl();
	private BankAccountDataService bankAccountDataService = new BankAccountDataImpl();
	private CompanyDataCenterService companyDataCenterService = new CompanyDataCenterImpl();
	private CompanyDataHallService companyDataHallService = new CompanyDataHallImpl();
	private ConfigurationDataService configurationDataService = new ConfigurationDataImpl();
	private AccountDataService accountDataService = new AccountDataImpl();
	private CompanyDataCarService companyDataCarService = new CompanyDataCarImpl();
	private MemberDataService<StaffPO> staffDatalService = new StaffDataImpl();
	private MemberDataService<DriverPO> driverDatalService = new DriverDataImpl();

	public InitialHelper() throws RemoteException {
		conn = ConnecterHelper.getConn();
	}

	public int getVersion() throws ClassNotFoundException {
		String file_path = ROOT;
		System.out.println(new File(file_path).listFiles().length + 1);
		return new File(file_path).listFiles().length + 1;
	}

	public OperationMessage saveFile(InitialDataPO po) throws ClassNotFoundException {
		int num = this.getVersion();
		po.setVersion(num + "");
		try {
			ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File(ROOT + num + ".txt")));
			oo.writeObject(po);
			oo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new OperationMessage(false, "打包时出错");
		} catch (IOException e) {
			e.printStackTrace();
			return new OperationMessage(false, "打包时出错：");
		}

		return new OperationMessage(true, "" + num);
	}

	public InitialDataPO loadFile(String version) {

		InitialDataPO po = null;
		try {
			System.out.println("version = " + version);
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(ROOT + version + ".txt")));
			po = (InitialDataPO) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return po;
	}

//	public static void main(String[] args) throws RemoteException {
//		InitialHelper helper = new InitialHelper();
//		InitialDataPO po = helper.loadFile("1");
//		System.out.println(po.getStoreModels().get(0).getCenterID());
//	}

	public OperationMessage clearMysql() {
		ArrayList<String> list = new ArrayList<String>() {
			{
				add("account");
				add("bank_account");
				add("car");
				add("center");
				add("centerout");
				add("city2d");
				add("deliver");
				add("driver");
				add("hall");
				add("load");
				add("log");
				add("order");
				add("pack");
				add("payment");
				add("price");
				add("proportion");
				add("receive");
				add("revenue");
				add("salary_strategy");
				add("staff");
				add("store_in");
				add("store_model");
				add("store_out");
			}

		};
		OperationMessage result = new OperationMessage();
		for (String tmp : list) {
			String delete = "delete from `" + tmp + "`";
			try {
				statement = conn.prepareStatement(delete);
				statement.executeUpdate();
			} catch (SQLException e) {
				result = new OperationMessage(false, "清空时出错：");
				System.err.println("清空时出错：");
				e.printStackTrace();
			}
		}
		return result;
	}

	// public static void main(String[] args) throws RemoteException,
	// ClassNotFoundException {
	// InitialHelper p = new InitialHelper();
	// ArrayList<AccountPO> list = new ArrayList<AccountPO>();
	// list.add(new AccountPO("admin", "HAVE", "admin"));
	// InitialDataPO po = new InitialDataPO("0", "空版本",
	// p.storeModelDataService.getModels(),
	// new ArrayList<BankAccountPO>(), new ArrayList<CarPO>(), new
	// ArrayList<StaffPO>(),
	// new ArrayList<HallPO>(), new ArrayList<CenterPO>(),
	// p.configurationDataService.getAllCity2D(),
	// p.configurationDataService.getPrice(),
	// p.configurationDataService.getProportion(),
	// p.configurationDataService.getSalaryStrategy(),
	// p.configurationDataService.getPack(), list);
	// p.saveFile(po);
	// }

	public InitialDataPO saveMysql(String dbName) throws ClassNotFoundException, RemoteException {
		ArrayList<HallPO> halls = companyDataHallService.getHall();
		ArrayList<String> hallIDs = new ArrayList<String>();
		ArrayList<CarPO> cars = new ArrayList<CarPO>();
		for (HallPO tmp : halls)
			hallIDs.add(tmp.getHallID());
		for (String tmp : hallIDs)
			cars.addAll(companyDataCarService.getCars(tmp));

		ArrayList<StaffPO> staffs = new ArrayList<StaffPO>();
		staffs.addAll(staffDatalService.getStaff(StaffTypeEnum.ADMINISTRATOR));
		staffs.addAll(staffDatalService.getStaff(StaffTypeEnum.BURSAR));
		staffs.addAll(staffDatalService.getStaff(StaffTypeEnum.CENTER_COUNTERMAN));
		staffs.addAll(staffDatalService.getStaff(StaffTypeEnum.DELIVER));
		staffs.addAll(staffDatalService.getStaff(StaffTypeEnum.HALL_COUNTERMAN));
		staffs.addAll(staffDatalService.getStaff(StaffTypeEnum.MANAGER));
		staffs.addAll(staffDatalService.getStaff(StaffTypeEnum.STOREMAN));
		staffs.addAll(driverDatalService.getStaff(StaffTypeEnum.DRIVER));

		return new InitialDataPO(getVersion() + "", dbName, storeModelDataService.getModels(),
				bankAccountDataService.getAll(), cars, staffs, halls, companyDataCenterService.getCenter(),
				configurationDataService.getAllCity2D(), configurationDataService.getPrice(),
				configurationDataService.getProportion(), configurationDataService.getSalaryStrategy(),
				configurationDataService.getPack(), accountDataService.getAccountPOs());
	}

	public OperationMessage loadMysql(InitialDataPO po) throws RemoteException {
		for (StoreModel tmp : po.getStoreModels()) {
			for (String label : tmp.getArea(StoreAreaCode.AIR).getShelfLabel()) {
				storeModelDataService.newShelf(tmp.getCenterID(), StoreAreaCode.AIR,
						Integer.parseInt(label.split("-")[0]), Integer.parseInt(label.split("-")[1]));
			}
			for (String label : tmp.getArea(StoreAreaCode.FLEX).getShelfLabel()) {
				storeModelDataService.newShelf(tmp.getCenterID(), StoreAreaCode.FLEX,
						Integer.parseInt(label.split("-")[0]), Integer.parseInt(label.split("-")[1]));
			}
			for (String label : tmp.getArea(StoreAreaCode.RAIL).getShelfLabel()) {
				storeModelDataService.newShelf(tmp.getCenterID(), StoreAreaCode.RAIL,
						Integer.parseInt(label.split("-")[0]), Integer.parseInt(label.split("-")[1]));
			}
			for (String label : tmp.getArea(StoreAreaCode.ROAD).getShelfLabel()) {
				storeModelDataService.newShelf(tmp.getCenterID(), StoreAreaCode.ROAD,
						Integer.parseInt(label.split("-")[0]), Integer.parseInt(label.split("-")[1]));
			}
		}
		for (BankAccountPO tmp : po.getBankAccounts()) {
			bankAccountDataService.insert(tmp);
		}
		for (CarPO tmp : po.getCars()) {
			companyDataCarService.addCar(tmp);
		}
		for (StaffPO tmp : po.getStaffs()) {
			if (tmp.getStaff().equals(StaffTypeEnum.DRIVER))
				driverDatalService.addStaff((DriverPO) tmp);
			else
				staffDatalService.addStaff(tmp);
		}
		for (HallPO tmp : po.getHalls()) {
			companyDataHallService.addHall(tmp);
		}
		for (CenterPO tmp : po.getCenters()) {
			companyDataCenterService.addCenter(tmp);
		}
		for (City2DPO tmp : po.getCity2dpos()) {
			configurationDataService.newCity2D(tmp);
		}
		configurationDataService.newPrice(po.getPricePO());
		configurationDataService.newPack(po.getPackPO());
		configurationDataService.newProportion(po.getProportionPO());
		configurationDataService.newSalaryStrategy(po.getSalaryStrategyPO());
		for (AccountPO tmp : po.getAccountPOs()) {
			accountDataService.insert(tmp);
		}
		return new OperationMessage();
	}

	public OperationMessage deleteFile(String version) {
		File f = new File(ROOT + version + ".txt");
		if (f.exists())
			f.delete();
		return new OperationMessage(true, "删除成功");
	}

}
