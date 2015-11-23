package rmiImpl;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import rmi.DataFactoryService;
import rmi.DataService;
import rmi.chatRemindService.ChatRemindService;
import rmi.examineService.ExamineManageService;
import rmi.examineService.ExamineSubmitService;
import rmiImpl.accountdata.AccountDataImpl;
import rmiImpl.chatRemindImpl.ChatRemindImpl;
import rmiImpl.companydata.CompanyDataCarImpl;
import rmiImpl.companydata.CompanyDataCenterImpl;
import rmiImpl.companydata.CompanyDataHallImpl;
import rmiImpl.configurationdata.ConfigurationDataImpl;
import rmiImpl.deliverdata.DeliverDataImpl;
import rmiImpl.examineImpl.ExamineManageImpl;
import rmiImpl.examineImpl.ExamineSubmitImpl;
import rmiImpl.financedata.FinanceDataImpl;
import rmiImpl.initaldata.InitialDataImpl;
import rmiImpl.memberdata.StaffDataImpl;
import rmiImpl.orderdata.OrderDataImpl;
import rmiImpl.receivedata.ReceiveDataImpl;
import rmiImpl.storedata.StoreDataImpl;
import rmiImpl.transportdata.CenterOutDataImpl;
import rmiImpl.transportdata.LoadDataImpl;

/**
 * 数据层，接口处理器
 * 
 * @author wjc
 * @version 2014.10.31
 */
public class DataFactory implements DataFactoryService {

	/**
	 * 注意：在jdk1.7之后的版本才支持case String
	 * 
	 * @param name data的名字
	 * @return DataService的子类实现
	 * @throws RemoteException
	 * @author wjc
	 * @version 2015.10.31
	 */
	public DataService<?> createDataService(String name) throws RemoteException {
		// TODO Auto-generated method stub
		switch (name) {
		case AccountDataImpl.NAME:
			return new AccountDataImpl();
		case CompanyDataCarImpl.NAME:
			return new CompanyDataCarImpl();
		case CompanyDataCenterImpl.NAME:
			return new CompanyDataCenterImpl();
		case CompanyDataHallImpl.NAME:
			return new CompanyDataHallImpl();
		case ConfigurationDataImpl.NAME:
			return new ConfigurationDataImpl();
		case DeliverDataImpl.NAME:
			return new DeliverDataImpl();
		case FinanceDataImpl.NAME:
			try {
				return new FinanceDataImpl();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		case InitialDataImpl.NAME:
			return new InitialDataImpl();
		case StaffDataImpl.NAME:
			return new StaffDataImpl();
		case OrderDataImpl.NAME:
			return new OrderDataImpl();
		case ReceiveDataImpl.NAME:
			return new ReceiveDataImpl();
		case StoreDataImpl.NAME:
			try {
				return new StoreDataImpl();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case CenterOutDataImpl.NAME:
			return new CenterOutDataImpl();
		case LoadDataImpl.NAME:
			return new LoadDataImpl();
		default:
			return null;
		}
	}

	@Override
	public ExamineManageService creatExamineManageService()
			throws RemoteException {
		// TODO Auto-generated method stub
		return new ExamineManageImpl();
	}

	@Override
	public ExamineSubmitService creatExamineSubmitService()
			throws RemoteException {
		// TODO Auto-generated method stub
		return new ExamineSubmitImpl();
	}

	@Override
	public ChatRemindService creatChatRemindService() throws RemoteException {
		// TODO Auto-generated method stub
		return new ChatRemindImpl();
	}
}
