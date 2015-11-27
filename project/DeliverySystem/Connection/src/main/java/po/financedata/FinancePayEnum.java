package po.financedata;

import util.EnumObservable;

/**
 * Client//vo.financevo//FinancePayEnum.java
 * @author CXWorks
 * @date 2015年11月26日 下午11:22:32
 * @version 1.0 
 */
public enum FinancePayEnum implements EnumObservable<FinancePayEnum> {

    RENT("租金"),
    TRANSPORTION("运费"),
    SALARY("工资"),
    AWARD("奖励");

    FinancePayEnum(String c){
        chinese = c;
    }

    private String chinese;

    public String getChinese(){
        return chinese;
    }
}
