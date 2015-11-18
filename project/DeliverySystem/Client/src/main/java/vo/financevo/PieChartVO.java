package vo.financevo;

import vo.FormVO;
import vo.InfoVO;

import java.util.Map;

import po.InfoEnum;

/**
 * Created by Sissel on 2015/10/24.
 */
public class PieChartVO extends InfoVO {
    // 饼状图的标题
    String	title;
    // 描述比较的项目的类型，比如支出，收入
    String	valueType;
    // 饼状图的每个项的名字及原始数值
    Map<String, Double>	originMap;
    // 饼状图的每个项的名字及最终比例
    Map<String,Double> ratioMap;
    public PieChartVO(){
    	super(InfoEnum.PIE_CHART);
    }
}
