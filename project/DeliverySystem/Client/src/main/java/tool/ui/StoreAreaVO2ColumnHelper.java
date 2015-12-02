package tool.ui;

import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import vo.storevo.StoreAreaInfoVO;

public class StoreAreaVO2ColumnHelper extends VO2ColumnHelper<StoreAreaInfoVO> {

	@Override
	public Set<Entry<String, String>> VO2Entries(StoreAreaInfoVO vo) {

        Map<String, String> map = new HashMap<>();

        map.put("区域名称", vo.getArea().getChinese());
        map.put("总排数", vo.getTotalRows() + "");
        map.put("总货架数", vo.getTotalShelves() + "");
        map.put("占用比例", vo.getUsedProporttion() + "");
        return map.entrySet();
	}

}
