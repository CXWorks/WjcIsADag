package tool.ui;

import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import vo.ordervo.OrderVO;
import vo.transitvo.TransitVO;

/**
 * Created by WJC on 2015/12/01.
 */
public class TransitVO2ColumnHelper extends VO2ColumnHelper<TransitVO> {

	@Override
	public Set<Entry<String, String>> VO2Entries(TransitVO vo) {

        Map<String, String> map = new HashMap<>();

        map.put("装车日期", vo.getLoadDate().toString());
        map.put("到达地", vo.getPlaceTo());
        map.put("监装员", vo.getPeopleSee());
        return map.entrySet();
	}

}
