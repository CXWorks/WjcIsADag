package po;

import util.EnumObservable;

import java.io.Serializable;

public enum FormEnum implements Serializable, EnumObservable<FormEnum> {

	ORDER("订单"),
    DELIVER("派件单"),
    PAYMENT("付款单"),
    REVENUE("收款单"),
    RECEIVE("到达单"),
    TRANSPORT_HALL("中转单"),
    TRANSPORT_CENTER("中转单"),
    STORE_IN("入库单"),
    STORE_OUT("出库单");

    private String chinese;

    FormEnum(String c){
        this.chinese = c;
    }

	public String getChinese(){
        return chinese;
    }
}
