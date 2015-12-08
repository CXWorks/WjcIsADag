package bl.blService.manageblService;

import java.util.ArrayList;

import message.OperationMessage;
import vo.managevo.car.CarVO;
import vo.managevo.institution.HallVO;

/** 
 * Client//blService.manageblService//ManageblCarService.java
 * @author CXWorks
 * @date 2015年10月25日 下午3:18:03
 * @version 1.0 
 */
public interface ManageblCarService {
	
	public ArrayList<CarVO> getCar(String hallID);
	
	public OperationMessage addCar(CarVO car);
	
	public OperationMessage modifyCar(CarVO car);
	
	public OperationMessage deleteCar(CarVO car);
	
	public CarVO searchCar(String  carID);
	
	public String newCarID();
}
