package bl.blImpl.manangrbl;

import java.util.ArrayList;

import message.OperationMessage;
import vo.managevo.car.CarVO;
import vo.managevo.institution.HallVO;
import bl.blService.manageblService.ManageblCarService;

/** 
 * Client//blImpl.manangrbl//ManageblCarImpl.java
 * @author CXWorks
 * @date 2015年10月26日 上午8:37:34
 * @version 1.0 
 */
public class CarManage implements ManageblCarService {

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCarService#getCar(vo.managevo.institution.HallVO)
	 */
	public ArrayList<CarVO> getCar(HallVO itself) {
		// TODO Auto-generated method stub
		ArrayList<CarVO> result=new ArrayList<CarVO>();
		CarVO stub=new CarVO();
		result.add(stub);
		return result;
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCarService#addCar(vo.managevo.car.CarVO)
	 */
	public OperationMessage addCar(CarVO car) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCarService#modifyCar(vo.managevo.car.CarVO)
	 */
	public OperationMessage modifyCar(CarVO car) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCarService#deleteCar(vo.managevo.car.CarVO)
	 */
	public OperationMessage deleteCar(CarVO car) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCarService#searchCar(vo.managevo.car.CarVO)
	 */
	public CarVO searchCar(CarVO car) {
		// TODO Auto-generated method stub
		return new CarVO();
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCarService#newCarID()
	 */
	public String newCarID() {
		// TODO Auto-generated method stub
		return "1111";
	}

}
