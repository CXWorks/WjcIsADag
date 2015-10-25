package rmi.companydata;

import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.CarPO;

/** 
 * Connection//rmi.companydata//CompanyDataCarService.java
 * @author CXWorks
 * @date 2015年10月25日 下午2:53:09
 * @version 1.0 
 */
public interface CompanyDataCarService {
	
	public ArrayList<CarPO> getCar();
	
	public String newCarID();
	
	public OperationMessage addCar(CarPO car);
	
	public OperationMessage deleteCar(CarPO car);
	
	public OperationMessage modifyCar(CarPO car);
}
