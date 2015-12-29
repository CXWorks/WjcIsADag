package bl.blImpl.storebl;

import bl.NetReconnect.Reconnect;
import bl.blService.storeblService.StoreModelBLService;
import bl.clientNetCache.CacheHelper;
import message.OperationMessage;
import model.store.StoreArea;
import model.store.StoreAreaCode;
import model.store.StoreLocation;
import model.store.StoreModel;
import rmi.storedata.StoreModelDataService;
import userinfo.UserInfo;
import util.R;
import vo.storevo.StoreAreaInfoVO;
import vo.storevo.StoreShelfVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observer;

/**
 * Created by Sissel on 2015/10/26.
 */
public class StoreModelBLImpl implements StoreModelBLService {
	private StoreWarningChecker storeWarningChecker;
	public StoreModelBLImpl(){

		
		this.storeWarningChecker=new StoreWarningChecker();
	}
    public OperationMessage setWarningLine(double percent) {
    	return this.storeWarningChecker.setWarningLine(percent);
    }
    //
    private boolean checkCondition(int first,int second){
    	return first<=second;
    }

    public OperationMessage expandPartition(String centerID,StoreAreaCode area, int number) {
    	StoreModelDataService storeModelDataService=CacheHelper.getStoreModelDataService();
    	 try {
 			StoreArea flex=storeModelDataService.getArea(UserInfo.getInstitutionID(),StoreAreaCode.FLEX);
 			int row=flex.getRowNumber();
 			int shelf=flex.getShelfNumber();
 			if (!checkCondition(number, shelf+row*50)) {
				return new OperationMessage(false, "size error");
			}
 			OperationMessage step1=storeModelDataService.removeShelf(UserInfo.getInstitutionID(),StoreAreaCode.FLEX, row, shelf);
 			//
 			StoreArea target=storeModelDataService.getArea(UserInfo.getInstitutionID(),area);
 			int rowt=target.getRowNumber();
 			int shelft=target.getShelfNumber();
 			if (shelft<50) {
 				shelft++;
 			} else {
 				shelft=1;
 				rowt++;
 			}
 			OperationMessage step2=storeModelDataService.newShelf(UserInfo.getInstitutionID(),area, rowt, shelft);
 			return new OperationMessage(step1.operationResult&&step2.operationResult,step1.getReason()+step2.getReason());
 		} catch (RemoteException e) {
 			Reconnect reconnect=new Reconnect();
 			return new OperationMessage(false, "not big enough");
 		}
    }

    public OperationMessage reducePartition(String centerID,StoreAreaCode area, int number) {
    	StoreModelDataService storeModelDataService=CacheHelper.getStoreModelDataService();
    	 try {
 			StoreArea target=storeModelDataService.getArea(UserInfo.getInstitutionID(),area);
 			int row=target.getRowNumber();
 			int shelf=target.getShelfNumber();
 			OperationMessage step1=storeModelDataService.removeShelf(UserInfo.getInstitutionID(),area, row, shelf);
 			if (!checkCondition(number, row*50+shelf)) {
				return new OperationMessage(false, "not big enough");
			}
 			//
 			StoreArea flex=storeModelDataService.getArea(UserInfo.getInstitutionID(),StoreAreaCode.FLEX);
 			int rowt=flex.getRowNumber();
 			int shelft=flex.getShelfNumber();
 			if (shelft<50) {
 				shelft++;
 			} else {
 				shelft=1;
 				rowt++;
 			}
 			OperationMessage step2=storeModelDataService.newShelf(UserInfo.getInstitutionID(),StoreAreaCode.FLEX, rowt, shelft);
 			return new OperationMessage(step1.operationResult&&step2.operationResult,step1.getReason()+step2.getReason());
 		} catch (RemoteException e) {
 			return new OperationMessage(false, "net error");
 		}
    }

	/* (non-Javadoc)
	 * @see bl.blService.storeblService.StoreModelBLService#moveShelf(model.store.StoreAreaCode, int, int, model.store.StoreAreaCode, int, int)
	 */
	@Override
	public OperationMessage moveShelf(String centerID,StoreAreaCode code_now, int row_now,
			int shelf_now, StoreAreaCode code, int row, int shelf) {
		StoreModelDataService storeModelDataService=CacheHelper.getStoreModelDataService();
		try {
			return storeModelDataService.moveShelf(UserInfo.getInstitutionID(),code_now, row_now, shelf_now, code, row, shelf);
		} catch (RemoteException e) {
			return new OperationMessage();
		}
	}
	/* (non-Javadoc)
	 * @see bl.blService.storeblService.StoreModelBLService#getShelfInfo(model.store.StoreAreaCode)
	 */
	@Override
	public ArrayList<StoreShelfVO> getShelfInfo(String centerID,StoreAreaCode storeAreaCode) {
		StoreModelDataService storeModelDataService=CacheHelper.getStoreModelDataService();
		try {
			StoreArea area=storeModelDataService.getArea(UserInfo.getInstitutionID(),storeAreaCode);
			int totalShelf=area.getShelfNumber();
			int totalRow=area.getRowNumber();
			ArrayList<StoreShelfVO> storeShelfVOs=new ArrayList<StoreShelfVO>(totalShelf);
			for(int i=1;i<=totalRow;i++){
				for (int j = 1; j <=totalShelf; j++) {
					ArrayList<StoreLocation> storeLocations=area.getByShelf(i, j);
					int k = 0;
					for (; k < storeLocations.size(); k++) {
						if (storeLocations.get(k).getOrderID().length()==0) {
							break;
						}
					}
					double usedProportion=k/(double)storeLocations.size();
					StoreShelfVO storeShelfVO=new StoreShelfVO(i, j, usedProportion);
					storeShelfVOs.add(storeShelfVO);
				}
			}
			return storeShelfVOs;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}

	}
	/* (non-Javadoc)
	 * @see bl.blService.storeblService.StoreModelBLService#getStoreAreaInfo(model.store.StoreAreaCode)
	 */
	@Override
	public StoreAreaInfoVO getStoreAreaInfo(String centerID,StoreAreaCode storeAreaCode) {
		StoreModelDataService storeModelDataService=CacheHelper.getStoreModelDataService();
		try {
			StoreArea storeArea=storeModelDataService.getArea(UserInfo.getInstitutionID(),storeAreaCode);
			StoreAreaInfoVO storeAreaInfoVO=new StoreAreaInfoVO(storeArea);
			return storeAreaInfoVO;
		} catch (RemoteException e) {
			return null;
		}
	}
	/* (non-Javadoc)
	 * @see bl.blService.storeblService.StoreModelBLService#getWarningLine()
	 */
	@Override
	public double getWarningLine() {
		return storeWarningChecker.getWarningLine();
	}

}
