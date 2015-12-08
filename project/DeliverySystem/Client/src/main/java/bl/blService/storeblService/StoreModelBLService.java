package bl.blService.storeblService;

import message.OperationMessage;
import model.store.StoreAreaCode;
import model.store.StoreLocation;

import java.util.ArrayList;
import java.util.Observer;

import vo.storevo.StoreAreaInfoVO;
import vo.storevo.StoreShelfVO;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface StoreModelBLService {

    public OperationMessage setWarningLine(double percent);

    public OperationMessage reducePartition(String centerID,StoreAreaCode area, int shelfNumber);

    public OperationMessage expandPartition(String centerID,StoreAreaCode area, int shelfNumber);

    public OperationMessage moveShelf(String centerID,StoreAreaCode code_now, int row_now,
			int shelf_now, StoreAreaCode code, int row, int shelf);
    
    
    public ArrayList<StoreShelfVO> getShelfInfo(String centerID,StoreAreaCode storeAreaCode); 
    
    public StoreAreaInfoVO getStoreAreaInfo(String centerID,StoreAreaCode storeAreaCode);
    
    public double getWarningLine();

}
