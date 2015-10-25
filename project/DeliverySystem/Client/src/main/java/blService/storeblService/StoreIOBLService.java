package blService.storeblService;

import message.CheckFormMessage;
import vo.storevo.GoodsVO;

import java.util.List;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface StoreIOBLService {

    public List<GoodsVO> getGoodsInfo(String inDate, String inTime, String outDate, String outTime);

    public List<GoodsVO> filterGoods(String orderNumber);

    public List<CheckFormMessage> checkFormat(String inDate, String inTime, String outDate, String outTime);

}
