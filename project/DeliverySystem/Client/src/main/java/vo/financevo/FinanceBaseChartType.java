package vo.financevo;

import util.EnumObservable;

/**
 * Created by Sissel on 2015/11/26.
 */
public enum FinanceBaseChartType implements EnumObservable<FinanceBaseChartType> {

    // 第一个描述每组各小项分别是对比什么，
    // 第二个描述按什么分组
    IO_MONTH("每月的收支"); // 按月分组，每组对比的是收入和支出



    private String chinese;

    FinanceBaseChartType(String c){
        this.chinese = c;
    }

    @Override
    public String getChinese() {
        return chinese;
    }
}
