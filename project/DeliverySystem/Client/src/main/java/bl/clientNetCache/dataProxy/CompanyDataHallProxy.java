package bl.clientNetCache.dataProxy;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bl.clientNetCache.save.CacheSaver;
import bl.clientNetCache.save.VersionSaver;
import bl.clientRMI.RMIHelper;
import message.OperationMessage;
import operation.Operation;
import po.companydata.HallPO;
import rmi.companydata.CompanyDataHallService;
import sun.security.jca.GetInstance;

/**
 * Client//bl.clientNetCache.dataProxy//CompanyDataHallProxy.java
 * @author CXWorks
 * @date 2015年12月23日 下午8:03:47
 * @version 1.0
 */
public class CompanyDataHallProxy extends DataProxy implements
		CompanyDataHallService {
	private static CompanyDataHallProxy proxy;
	//
	private static final String VERSION_NAME="companyHall";
	private static final String HALL="hallCache";
	//
	private long clientVersion;
	private CompanyDataHallService companyDataHallSrvice;
	//
	private ArrayList<HallPO> hallPOs;
	//
	private CompanyDataHallProxy(){}
	//
	public static CompanyDataHallService getInstance() throws RemoteException{
		if (proxy!=null) {
			return proxy;
		}
		proxy=new CompanyDataHallProxy();
		//
		VersionSaver versionSaver=new VersionSaver();
		proxy.clientVersion=versionSaver.loadVersion(VERSION_NAME);
		proxy.companyDataHallSrvice=RMIHelper.getCompanyDataHallService();
		//
		long serverVersion=proxy.companyDataHallSrvice.getLatestVersionID();
		//cache data
		CacheSaver cacheSaver=new CacheSaver();
		try {
			proxy.hallPOs=(ArrayList<HallPO>) cacheSaver.loadCache(HALL, true);
		} catch (IOException e) {
			proxy.clientVersion=Long.MAX_VALUE;
		}
		if (proxy.clientVersion>serverVersion) {
			proxy.hallPOs=proxy.companyDataHallSrvice.getHall();
			proxy.clientVersion=serverVersion;
		}else if (proxy.clientVersion<serverVersion) {
			List<Operation> operations=proxy.companyDataHallSrvice.getOperation(proxy.clientVersion);
			proxy.dealOperation(operations);
			proxy.clientVersion=serverVersion;
		}
		//
		return proxy;
	}

	/* (non-Javadoc)
	 * @see rmi.companydata.CompanyDataHallService#getHallByID(java.lang.String)
	 */
	@Override
	public HallPO getHallByID(String ID) throws RemoteException {
		return this.hallPOs.stream()
				.filter(hall->{return hall.getHallID().equalsIgnoreCase(ID);})
				.findFirst().get();
	}

	/* (non-Javadoc)
	 * @see rmi.companydata.CompanyDataHallService#getHall()
	 */
	@Override
	public ArrayList<HallPO> getHall() throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<HallPO>(hallPOs);
	}

	/* (non-Javadoc)
	 * @see rmi.companydata.CompanyDataHallService#addHall(po.companydata.HallPO)
	 */
	@Override
	public OperationMessage addHall(HallPO hall) throws RemoteException {
		hallPOs.add(hall);
		clientVersion++;
		return companyDataHallSrvice.addHall(hall);
	}

	/* (non-Javadoc)
	 * @see rmi.companydata.CompanyDataHallService#deleteHall(po.companydata.HallPO)
	 */
	@Override
	public OperationMessage deleteHall(HallPO hall) throws RemoteException {
		hallPOs.removeIf(hal->{return hal.getHallID().equalsIgnoreCase(hall.getHallID());});
		clientVersion++;
		return companyDataHallSrvice.deleteHall(hall);
	}

	/* (non-Javadoc)
	 * @see rmi.companydata.CompanyDataHallService#modifyHall(po.companydata.HallPO)
	 */
	@Override
	public OperationMessage modifyHall(HallPO hall) throws RemoteException {
		this.deleteHall(hall);
		this.addHall(hall);
		clientVersion--;//调用了两次其它方法，要减回去
		return companyDataHallSrvice.deleteHall(hall);
	}

	/* (non-Javadoc)
	 * @see rmi.companydata.CompanyDataHallService#newHallID(java.lang.String)
	 */
	@Override
	public String newHallID(String city) throws RemoteException {
		return companyDataHallSrvice.newHallID(city);
	}

	/* (non-Javadoc)
	 * @see bl.clientNetCache.dataProxy.DataProxy#dealOperation(java.util.List)
	 */
	@Override
	protected void dealOperation(List<Operation> operations)
			throws RemoteException {
		for (Operation operation : operations) {
			switch (operation.operationTypeEnum) {
			case NEW:
				this.addHall((HallPO)operation.src);
				break;
			case DELETE:
				this.deleteHall((HallPO)operation.src);
				break;
			case MODIFY:
				this.modifyHall((HallPO)operation.src);
				break;

			default:
				break;
			}
		}

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

}
