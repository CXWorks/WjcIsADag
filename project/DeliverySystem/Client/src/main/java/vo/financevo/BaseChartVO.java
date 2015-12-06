package vo.financevo;

import po.InfoEnum;
import vo.InfoVO;

/**
 * Created by Sissel on 2015/10/24.
 * 此类为直方图和折线图的共用数据集合类
 */
public class BaseChartVO extends InfoVO{
    public String	title;
    public String	mainXType;
    public String	mainYType;

    public double	ySpan;
    public double	ybegin;
    public double	yend;

    public String[] categories;
    public String[] elements;

    public double[][]	values;

    public BaseChartVO(){
    	super(InfoEnum.BASE_CHART);
    }
}

