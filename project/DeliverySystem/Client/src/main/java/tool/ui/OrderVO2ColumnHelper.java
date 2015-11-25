package tool.ui;

import vo.FormVO;
import vo.ordervo.OrderVO;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sissel on 2015/11/24.
 */
public class OrderVO2ColumnHelper extends VO2ColumnHelper<OrderVO> {
	
    @Override
    public Set<Map.Entry<String, String>> VO2Entries(OrderVO vo) {
    	
        Map<String, String> map = new HashMap<>();

        map.put("订单号", vo.getFormID());
        map.put("寄件人", vo.getNameFrom());
        map.put("收件人", vo.getNameTo());
        map.put("寄出地", vo.getAddressFrom());
        map.put("目的地", vo.getAddressTo());
        map.put("货物名称", vo.getGoodsName());
        map.put("货物类型", vo.getGoodsType());
        map.put("寄件人手机", vo.getPhoneNumFrom());
        map.put("收件人手机", vo.getPhoneNumTo());
        map.put("体积", vo.getVolume());
        map.put("重量", vo.getWeight());
        map.put("价格", vo.getMoney());
        return map.entrySet();
    }

}
