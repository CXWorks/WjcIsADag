package vo.financevo;

import vo.FormVO;
import vo.InfoVO;

import java.util.HashMap;
import java.util.Map;

import po.InfoEnum;
import po.financedata.FinancePayEnum;

/**
 * Created by Sissel on 2015/10/24.
 */
public class PieChartVO extends InfoVO {
    // 饼状图的标题
    public String	title;
    // 描述比较的项目的类型，比如支出，收入
    public String	valueType;
    // 饼状图的每个项的名字及原始数值
    public Map<String, Double>	originMap;

    public PieChartVO(){
    	super(InfoEnum.PIE_CHART);
    }
    //
}
