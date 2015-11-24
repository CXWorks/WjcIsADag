package bl.blImpl.manangrbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.companydata.CarPO;
import rmi.companydata.CompanyDataCarService;
import message.OperationMessage;
import userinfo.UserInfo;
import vo.managevo.car.CarVO;
import vo.managevo.institution.HallVO;
import bl.blService.manageblService.ManageblCarService;
import bl.clientNetCache.CacheHelper;
import tool.vopo.VOPOFactory;

/** 
 * Client//blImpl.manangrbl//ManageblCarImpl.java
 * @author CXWorks
 * @date 2015年10月26日 上午8:37:34
 * @version 1.0 
 */
public class CarManage implements ManageblCarService {
	private CompanyDataCarService companyDataCarService;
	private VOPOFactory vopoFactory;
	public CarManage(VOPOFactory vopoFactory){
		this.vopoFactory=vopoFactory;
		this.companyDataCarService=CacheHelper.getCompanyDataCarService();
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCarService#getCar(vo.managevo.institution.HallVO)
	 */
	public ArrayList<CarVO> getCar(HallVO itself) {
		try {
			ArrayList<CarPO> po=companyDataCarService.getCars(itself.getHallID());
			ArrayList<CarVO> vo=new ArrayList<CarVO>(po.size());
			for(int i=0;i<po.size();i++){
				CarPO each=po.get(i);
				CarVO temp=(CarVO)vopoFactory.transPOtoVO(each);
				vo.add(temp);
			}
			return vo;
		} catch (RemoteException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCarService#addCar(vo.managevo.car.CarVO)
	 */
	public OperationMessage addCar(CarVO car) {
		CarPO po=(CarPO)vopoFactory.transVOtoPO(car);
		try {
			return companyDataCarService.addCar(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCarService#modifyCar(vo.managevo.car.CarVO)
	 */
	public OperationMessage modifyCar(CarVO car) {
		CarPO po=(CarPO)vopoFactory.transVOtoPO(car);
		try {
			return companyDataCarService.modifyCar(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCarService#deleteCar(vo.managevo.car.CarVO)
	 */
	public OperationMessage deleteCar(CarVO car) {
		CarPO po=(CarPO)vopoFactory.transVOtoPO(car);
		try {
			return companyDataCarService.deleteCar(po);
		} catch (RemoteException e) {
			return new OperationMessage(false, "net error");
		}
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCarService#searchCar(vo.managevo.car.CarVO)
	 */
	public CarVO searchCar(CarVO car) {
		String ID=car.getCarID();
		try {
			CarPO po= companyDataCarService.getCar(ID);
			CarVO ans=(CarVO)vopoFactory.transPOtoVO(po);
			return ans;
		} catch (RemoteException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see blService.manageblService.ManageblCarService#newCarID()
	 */
	public String newCarID() {
		try {
			String carID=companyDataCarService.newCarID(UserInfo.getInstitutionID());
			return carID;
		} catch (RemoteException e) {
			return null;
		}
	}

}
