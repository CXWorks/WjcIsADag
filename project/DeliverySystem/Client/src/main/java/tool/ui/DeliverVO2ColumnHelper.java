package tool.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javafx.scene.control.TableColumn;
import vo.delivervo.DeliverVO;
import vo.ordervo.OrderVO;

public class DeliverVO2ColumnHelper extends VO2ColumnHelper<OrderVO>{

	@Override
	public Set<Map.Entry<String, String>> VO2Entries(OrderVO vo) {

	       Map<String, String> map = new HashMap<>();

	      //  map.put("派件时间", dvo.getDate().toString());
	        map.put("订单号", vo.getFormID());
	        map.put("目的地", vo.getAddressTo());
	        map.put("收件人", vo.getNameTo());


	        return map.entrySet();
	        
	}
	

	


}
