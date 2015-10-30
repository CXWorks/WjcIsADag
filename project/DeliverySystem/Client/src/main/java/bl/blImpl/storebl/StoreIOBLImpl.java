package bl.blImpl.storebl;

import bl.blService.storeblService.StoreIOBLService;
import message.CheckFormMessage;
import vo.storevo.GoodsVO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sissel on 2015/10/26.
 */
public class StoreIOBLImpl implements StoreIOBLService {
    public List<GoodsVO> getGoodsInfo(String inDate, String inTime, String outDate, String outTime) {
        return new LinkedList<GoodsVO>();
    }

    public List<GoodsVO> filterGoods(String orderNumber) {
        return new LinkedList<GoodsVO>();
    }

    public List<CheckFormMessage> checkFormat(String inDate, String inTime, String outDate, String outTime) {
        return new LinkedList<CheckFormMessage>();
    }
}
