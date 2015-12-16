package po;

import util.EnumObservable;

/**
 * Connection//po//InfoEnum.java
 * @author CXWorks
 * @date 2015年11月18日 下午3:20:51
 * @version 1.0 
 */
public enum InfoEnum implements EnumObservable<InfoEnum> {
	STORE_SHELF(""),
    ACCOUNT(""),
    CAR(""),
    CENTER("中转中心"),
    HALL("营业厅"),
    CITY_2D(""),
    CITY_DISTANCE(""),
    PACK(""),
    PRICE(""),
    PROPORTION(""),
    SALARY(""),
    BANK_ACCOUNT(""),
    INITIAL_DATA(""),
    STAFF(""),
    BASE_CHART(""),
    CALCULATE(""),
    PIE_CHART(""),
    LOGISTICS(""),
    GOODS(""),
    STOCK_TACK(""),
    LOG(""),
    DRIVER("");

    String chinese;

    InfoEnum(String c){
        chinese = c;
    }

    @Override
    public String getChinese() {
        return chinese;
    }
}
