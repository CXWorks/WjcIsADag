package tool.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import vo.logisticsvo.LogisticsVO;

public class LogisticsVO2ColumnHelper extends VO2ColumnHelper<LogisticsVO> {
	 @Override
	    public Set<Map.Entry<String, String>> VO2Entries(LogisticsVO vo) {
	    	
	        Map<String, String> map = new HashMap<>();
	        map.put("寄件人", vo.getNameFrom());
	        map.put("收件人", vo.getNameTo());
	        map.put("货物名称", vo.getGoodsName());
	        map.put("收件人手机", vo.getPhoneNumTo());
	        map.put("货物类型", vo.getType().toString());

	        return map.entrySet();
	    }
	
	
	
}
