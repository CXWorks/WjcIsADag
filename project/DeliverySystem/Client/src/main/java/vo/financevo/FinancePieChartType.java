package vo.financevo;

import util.EnumObservable;

/**
 * Created by Sissel on 2015/11/26.
 */
public enum FinancePieChartType implements EnumObservable<FinancePieChartType>{
    MONTH_IN_PAYMENT("各个月支出"),
    MONTH_IN_REVENUE("各个月收入"),
    TYPES_IN_PAYMENT("各类型支出");

    private String chinese;

    FinancePieChartType(String c){
        this.chinese = c;
    }

    @Override
    public String getChinese() {
        return chinese;
    }
}
