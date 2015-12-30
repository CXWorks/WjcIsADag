package bl.clientNetCache.dataProxy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import bl.clientNetCache.save.CacheSaver;
import bl.clientNetCache.save.VersionSaver;
import bl.clientRMI.RMIHelper;
import message.OperationMessage;
import operation.Operation;
import po.configurationdata.City2DPO;
import po.configurationdata.PackPO;
import po.configurationdata.PricePO;
import po.configurationdata.ProportionPO;
import po.configurationdata.SalaryStrategyPO;
import rmi.configurationdata.ConfigurationDataService;
import userinfo.UserInfo;

/**
 * Client//bl.clientNetCache.dataProxy//ConfigurationDataProxy.java
 * @author CXWorks
 * @date 2015年12月17日 下午11:36:39
 * @version 1.0
 */
public class ConfigurationDataProxy extends DataProxy implements ConfigurationDataService {
	private static ConfigurationDataProxy proxy;
	//
	private static final String VERSION_NAME="configurationCacheVersion";
	private static final String CITY="cityCache";
	private static final String SALARY="salaryCache";
	private static final String PROPORTION="proportionCache";
	private static final String PACK="packCache";
	private static final String PRICE="priceCache";
	//
	private ConfigurationDataService configurationDataService;
	private long clientVersion;
	//
	private ArrayList<City2DPO> city;
	private ArrayList<SalaryStrategyPO> salary;
	private PricePO price;
	private ProportionPO proportion;
	private PackPO pack;
	private double warningLine;

	private ConfigurationDataProxy(){}
	//
	public static ConfigurationDataService getInstance() throws RemoteException{
		if (proxy!=null) {
			return proxy;
		}
		//
		proxy=new ConfigurationDataProxy();
		//version
		VersionSaver versionSaver=new VersionSaver();
		proxy.clientVersion=versionSaver.loadVersion(VERSION_NAME);
		proxy.configurationDataService=RMIHelper.getConfigurationDataService();
		long serverVersion=proxy.configurationDataService.getLatestVersionID();
		//cache data
		CacheSaver saver=new CacheSaver();
		try {
			proxy.city = (ArrayList<City2DPO>) saver.loadCache(CITY, true);
			proxy.salary = (ArrayList<SalaryStrategyPO>) saver.loadCache(
					SALARY, true);
			proxy.pack = (PackPO) saver.loadCache(PACK);
			proxy.price = (PricePO) saver.loadCache(PRICE);
			proxy.proportion = (ProportionPO) saver.loadCache(PROPORTION);
			proxy.warningLine = proxy.configurationDataService
					.getWarningline(UserInfo.getInstitutionID());
		} catch (IOException e) {
			proxy.clientVersion = Long.MAX_VALUE;
		}
		//
		if (serverVersion>proxy.clientVersion) {
			List<Operation> operations=proxy.configurationDataService.getOperation(proxy.clientVersion);
			proxy.dealOperation(operations);
			proxy.clientVersion=serverVersion;
		}
		else if (serverVersion<proxy.clientVersion) {
			proxy.city=proxy.configurationDataService.getAllCity2D();
			proxy.pack=proxy.configurationDataService.getPack();
			proxy.price=proxy.configurationDataService.getPrice();
			proxy.proportion=proxy.configurationDataService.getProportion();
			proxy.salary=proxy.configurationDataService.getSalaryStrategy();
			proxy.warningLine=proxy.configurationDataService.getWarningline(UserInfo.getInstitutionID());
			proxy.clientVersion=serverVersion;
		}
		//
		return proxy;
	}
	//
	public static void Close(){
		VersionSaver versionSaver=new VersionSaver();
		versionSaver.saveVersion(proxy.clientVersion, VERSION_NAME);
		CacheSaver cacheSaver=new CacheSaver();
		cacheSaver.saveCache(proxy.pack, PACK);
		cacheSaver.saveCache(proxy.price, PRICE);
		cacheSaver.saveCache(proxy.proportion, PROPORTION);
		cacheSaver.saveCache(proxy.city, CITY);
		cacheSaver.saveCache(proxy.salary, SALARY);
	}

	/* (non-Javadoc)
	 * @see rmi.cachedata.CacheDataService#getLatestVersionID()
	 */
	@Override
	public long getLatestVersionID() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see rmi.cachedata.CacheDataService#getOperation(long)
	 */
	@Override
	public List<Operation> getOperation(long localVersion)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#newCity2D(po.configurationdata.City2DPO)
	 */
	@Override
	public OperationMessage newCity2D(City2DPO po) throws RemoteException {
		city.add(po);
		proxy.clientVersion++;
		return configurationDataService.newCity2D(po);
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#deleteCity2D(java.lang.String)
	 */
	@Override
	public OperationMessage deleteCity2D(String name) throws RemoteException {
		proxy.city.removeIf(ci->{return ci.getName().equalsIgnoreCase(name);});
		proxy.clientVersion++;
		return configurationDataService.deleteCity2D(name);
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#modifyCity2D(po.configurationdata.City2DPO)
	 */
	@Override
	public OperationMessage modifyCity2D(City2DPO po) throws RemoteException {
		proxy.deleteCity2D(po.getName());
		proxy.city.add(po);
		return configurationDataService.modifyCity2D(po);
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#getCity2D(java.lang.String)
	 */
	@Override
	public City2DPO getCity2D(String name) throws RemoteException {
		return proxy.city.stream()
				.filter(ci->{return ci.getName().equalsIgnoreCase(name);})
				.findFirst().get();
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#getAllCity2D()
	 */
	@Override
	public ArrayList<City2DPO> getAllCity2D() throws RemoteException {
		return proxy.city;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#clearCity2D()
	 */
	@Override
	public OperationMessage clearCity2D() throws RemoteException {
		proxy.city.clear();
		proxy.clientVersion++;
		return configurationDataService.clearCity2D();
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#getSalaryStrategy()
	 */
	@Override
	public ArrayList<SalaryStrategyPO> getSalaryStrategy()
			throws RemoteException {
		return proxy.salary;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#newSalaryStrategy(java.util.List)
	 */
	@Override
	public OperationMessage newSalaryStrategy(List<SalaryStrategyPO> po)
			throws RemoteException {
		proxy.salary.addAll(po);
		proxy.clientVersion++;
		return configurationDataService.newSalaryStrategy(po);
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#modifySalaryStrategy(po.configurationdata.SalaryStrategyPO)
	 */
	@Override
	public OperationMessage modifySalaryStrategy(SalaryStrategyPO salaryStrategy)
			throws RemoteException {
		proxy.salary.removeIf(sal->{return sal.getStaff().equals(salaryStrategy.getStaff());});
		proxy.salary.add(salaryStrategy);
		proxy.clientVersion++;
		return configurationDataService.modifySalaryStrategy(salaryStrategy);
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#getPack()
	 */
	@Override
	public PackPO getPack() throws RemoteException {
		return proxy.pack;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#newPack(po.configurationdata.PackPO)
	 */
	@Override
	public OperationMessage newPack(PackPO po) throws RemoteException {
		proxy.pack=po;
		proxy.clientVersion++;
		return configurationDataService.newPack(po);
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#modifyPack(po.configurationdata.PackPO)
	 */
	@Override
	public OperationMessage modifyPack(PackPO pack) throws RemoteException {
		proxy.pack=pack;
		proxy.clientVersion++;
		return configurationDataService.modifyPack(pack);
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#getPrice()
	 */
	@Override
	public PricePO getPrice() throws RemoteException {
		return proxy.price;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#newPrice(po.configurationdata.PricePO)
	 */
	@Override
	public OperationMessage newPrice(PricePO po) throws RemoteException {
		proxy.price=po;
		proxy.clientVersion++;
		return configurationDataService.newPrice(po);
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#modifyPrice(po.configurationdata.PricePO)
	 */
	@Override
	public OperationMessage modifyPrice(PricePO price) throws RemoteException {
		proxy.price=price;
		proxy.clientVersion++;
		return configurationDataService.modifyPrice(price);
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#getProportion()
	 */
	@Override
	public ProportionPO getProportion() throws RemoteException {
		return proxy.proportion;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#modifyProportion(po.configurationdata.ProportionPO)
	 */
	@Override
	public OperationMessage modifyProportion(ProportionPO proportion)
			throws RemoteException {
		proxy.proportion=proportion;
		proxy.clientVersion++;
		return configurationDataService.modifyProportion(proportion);
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#newProportion(po.configurationdata.ProportionPO)
	 */
	@Override
	public OperationMessage newProportion(ProportionPO po)
			throws RemoteException {
		proxy.proportion=po;
		proxy.clientVersion++;
		return configurationDataService.newProportion(po);
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#getWarningline(java.lang.String)
	 */
	@Override
	public double getWarningline(String centerID) throws RemoteException {
		return proxy.warningLine;
	}

	/* (non-Javadoc)
	 * @see rmi.configurationdata.ConfigurationDataService#setWarningline(java.lang.String, double)
	 */
	@Override
	public OperationMessage setWarningline(String centerID, double value)
			throws RemoteException {
		proxy.warningLine=value;
		proxy.clientVersion++;
		return configurationDataService.setWarningline(centerID, value);
	}
	/* (non-Javadoc)
	 * @see bl.clientNetCache.dataProxy.DataProxy#dealOperation(java.util.List)
	 */
	@Override
	protected void dealOperation(List<Operation> operations) throws RemoteException {
		//TODO 这次大作业写得最差的代码QAQ
		for (Operation operation : operations) {
			switch (operation.operationTypeEnum) {
			case NEW:
				switch (operation.infoEnum) {
				case CITY_2D:
					proxy.newCity2D((City2DPO)operation.src);
					break;
				case PRICE:
					proxy.newPrice((PricePO)operation.src);
					break;
				case PROPORTION:
					proxy.newProportion((ProportionPO)operation.src);
					break;
				case PACK:
					proxy.newPack((PackPO)operation.src);
					break;
				default:
					break;
				}
				break;
			case MODIFY:
				switch (operation.infoEnum) {
				case CITY_2D:
					proxy.modifyCity2D((City2DPO)operation.src);
					break;
				case PRICE:
					proxy.modifyPrice((PricePO)operation.src);
					break;
				case PROPORTION:
					proxy.modifyProportion((ProportionPO)operation.src);
					break;
				case PACK:
					proxy.modifyPack((PackPO)operation.src);
					break;
				default:
					break;
				}
				break;
			case DELETE:
				switch (operation.infoEnum) {
				case CITY_2D:
					proxy.deleteCity2D(((City2DPO)operation.src).getName());
					break;
				default:
					break;
				}
				break;

			default:
				//do nothing
			}
		}

	}

}
