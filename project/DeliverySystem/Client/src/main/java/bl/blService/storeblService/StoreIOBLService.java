package bl.blService.storeblService;

import message.CheckFormMessage;
import vo.storevo.GoodsVO;
import vo.storevo.StoreFormVO;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface StoreIOBLService {

    public List<StoreFormVO> getGoodsInfo(Calendar startDate,Calendar endDate);

    public List<StoreFormVO> filterGoods(String orderNumber);

    public List<CheckFormMessage> checkFormat(String inDate, String inTime, String outDate, String outTime);

}
