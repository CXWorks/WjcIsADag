package vo.financevo;

import po.InfoEnum;
import vo.InfoVO;

/**
 * Created by Sissel on 2015/10/24.
 * 此类为直方图和折线图的共用数据集合类
 */
public class BaseChartVO extends InfoVO{
    String	title;
    String	mainXType;
    String	mainYType;

    double	ySpan;
    double	ybegin;
    double	yend;

    String[]	x1Classes;
    String[]	x2Classes;

    double[][]	values;

    public BaseChartVO(){
    	super(InfoEnum.BASE_CHART);
    }
}
