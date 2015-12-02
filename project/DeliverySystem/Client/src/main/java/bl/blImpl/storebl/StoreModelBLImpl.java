package bl.blImpl.storebl;

import bl.blService.storeblService.StoreModelBLService;
import bl.clientNetCache.CacheHelper;
import message.OperationMessage;
import model.store.StoreArea;
import model.store.StoreAreaCode;
import model.store.StoreLocation;
import model.store.StoreModel;
import rmi.storedata.StoreModelDataService;
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
	private StoreModelDataService storeModelDataService;
	public StoreModelBLImpl(){
		this.storeModelDataService=CacheHelper.getStoreModelDataService();
	}
    public OperationMessage setWarningLine(double percent) {
        return new OperationMessage();
    }

    public OperationMessage reducePartition(StoreAreaCode area, int number) {
    	 try {
 			StoreArea flex=storeModelDataService.getArea(StoreAreaCode.FLEX);
 			int row=flex.getRowNumber();
 			int shelf=flex.getShelfNumber();
 			OperationMessage step1=storeModelDataService.removeShelf(StoreAreaCode.FLEX, row, shelf);
 			//
 			StoreArea target=storeModelDataService.getArea(area);
 			int rowt=target.getRowNumber();
 			int shelft=target.getShelfNumber();
 			if (shelft<50) {
 				shelft++;
 			} else {
 				shelft=1;
 				rowt++;
 			}
 			OperationMessage step2=storeModelDataService.newShelf(area, rowt, shelft);
 			return new OperationMessage(step1.operationResult&&step2.operationResult,step1.getReason()+step2.getReason());
 		} catch (RemoteException e) {
 			return new OperationMessage(false, "net error");
 		}
    }

    public OperationMessage expandPartition(StoreAreaCode area, int number) {
    	 try {
 			StoreArea target=storeModelDataService.getArea(area);
 			int row=target.getRowNumber();
 			int shelf=target.getShelfNumber();
 			OperationMessage step1=storeModelDataService.removeShelf(area, row, shelf);
 			//
 			StoreArea flex=storeModelDataService.getArea(StoreAreaCode.FLEX);
 			int rowt=flex.getRowNumber();
 			int shelft=flex.getShelfNumber();
 			if (shelft<50) {
 				shelft++;
 			} else {
 				shelft=1;
 				rowt++;
 			}
 			OperationMessage step2=storeModelDataService.newShelf(StoreAreaCode.FLEX, rowt, shelft);
 			return new OperationMessage(step1.operationResult&&step2.operationResult,step1.getReason()+step2.getReason());
 		} catch (RemoteException e) {
 			return new OperationMessage(false, "net error");
 		}
    }
    
	/* (non-Javadoc)
	 * @see bl.blService.storeblService.StoreModelBLService#moveShelf(model.store.StoreAreaCode, int, int, model.store.StoreAreaCode, int, int)
	 */
	@Override
	public OperationMessage moveShelf(StoreAreaCode code_now, int row_now,
			int shelf_now, StoreAreaCode code, int row, int shelf) {
		try {
			return storeModelDataService.moveShelf(code_now, row_now, shelf_now, code, row, shelf);
		} catch (RemoteException e) {
			return new OperationMessage();
		}
	}
	/* (non-Javadoc)
	 * @see bl.blService.storeblService.StoreModelBLService#getShelfInfo(model.store.StoreAreaCode)
	 */
	@Override
	public ArrayList<StoreShelfVO> getShelfInfo(StoreAreaCode storeAreaCode) {
		try {
			StoreArea area=storeModelDataService.getArea(storeAreaCode);
			int totalShelf=area.getShelfNumber();
			int totalRow=area.getRowNumber();
			ArrayList<StoreShelfVO> storeShelfVOs=new ArrayList<StoreShelfVO>(totalShelf);
			for(int i=1;i<=totalRow;i++){
				for (int j = 0; j < totalShelf; j++) {
					ArrayList<StoreLocation> storeLocations=area.getByShelf(i, j);
					double usedProportion=storeLocations.size()/50;
					StoreShelfVO storeShelfVO=new StoreShelfVO(i, j, usedProportion);
					storeShelfVOs.add(storeShelfVO);
				}
			}
			return storeShelfVOs;
		} catch (RemoteException e) {
			return null;
		}
		
	}
	/* (non-Javadoc)
	 * @see bl.blService.storeblService.StoreModelBLService#getStoreAreaInfo(model.store.StoreAreaCode)
	 */
	@Override
	public StoreAreaInfoVO getStoreAreaInfo(StoreAreaCode storeAreaCode) {
		try {
			StoreArea storeArea=storeModelDataService.getArea(storeAreaCode);
			StoreAreaInfoVO storeAreaInfoVO=new StoreAreaInfoVO(storeArea);
			return storeAreaInfoVO;
		} catch (RemoteException e) {
			return null;
		}
	}

}
