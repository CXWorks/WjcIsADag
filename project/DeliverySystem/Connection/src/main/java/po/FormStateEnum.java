package po;

import util.EnumObservable;

import java.io.Serializable;

public enum FormStateEnum implements Serializable, EnumObservable<FormStateEnum> {

    DRAFT("草稿"),
    SUBMIT("提交"),
    PASS("审批通过"),
    CONSTRUCTED("创建中");

    private String chinese;

    private FormStateEnum(String chinese){
        this.chinese = chinese;
    }

    @Override
    public String getChinese() {
        return chinese;
    }
}
